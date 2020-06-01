@echo off

setlocal

rem *********************************************************
rem * Check if environment variable JAVA_HOME has been set. *
rem *********************************************************

if not "%JAVA_HOME%"=="" goto HomePresent
	echo JAVA_HOME (path to current Java installation) not set.
	goto :prompt
:HomePresent
if exist "%JAVA_HOME%\bin\java.exe" goto JavaPresent
	echo No java.exe found in JAVA_HOME (%JAVA_HOME%).
	goto :prompt
:JavaPresent


rem ***************************************************************
rem * Check if environment variable JFX_HOME has been set. This   *
rem * will not be done automatically during JavaFX installation!! *
rem ***************************************************************

set internalfx=
pushd %JAVA_HOME%
for /R %%i in (jfxrt.jar) do @if exist %%i set internalfx=%%i
if "%internalfx%"=="" for /R %%i in (javafx-swt.jar) do @if exist %%i set internalfx=%%i
popd

if not "%internalfx%"=="" goto :JavaWithFX
if not "%JFX_HOME%"=="" goto :FXPresent
	echo JFX_HOME (path to current JavaFX installation) not set.
	goto :prompt

:FXPresent

set classpath=%JFX_HOME%\lib\javafx.base.jar
set classpath=%classpath%;%JFX_HOME%\lib\javafx.controls.jar
set classpath=%classpath%;%JFX_HOME%\lib\javafx.fxml.jar
set classpath=%classpath%;%JFX_HOME%\lib\javafx.graphics.jar
set classpath=%classpath%;%JFX_HOME%\lib\javafx.media.jar
set classpath=%classpath%;%JFX_HOME%\lib\javafx.swing.jar
set classpath=%classpath%;%JFX_HOME%\lib\javafx.web.jar
set classpath=%classpath%;%JFX_HOME%\lib\javafx-swt.jar

set VM_Flags=--module-path %JFX_HOME%\lib --add-modules=javafx.controls,javafx.fxml
goto :AddExternals

:JavaWithFX

set VM_Flags=
set classpath=

:AddExternals

rem ****************************************************************
rem * Add jpos114, xalan-j and all other necessary jar files from  *
rem * sub-folder jar                                               *
rem ****************************************************************

for %%k in (jar\*.jar) do call :addtoclasspath %%~dpfk

rem ****************************************************************
rem * Now call the Java command                                    *
rem ****************************************************************

echo on
"%JAVA_HOME%\bin\java.exe" %VM_Flags% -cp "%classpath%" SPF_Test.Main

@if ERRORLEVEL 1 goto :prompt
@goto :EOF

### Subroutine prompt
# Prompts for pressing the enter key to exit.
#########################

:prompt
@echo off
set /P x=Press Enter to exit.
goto :EOF

### Subroutine addtoclasspath
# Adds given jar file to environment variable classpath
# and sets environment variable x to 1.
# Parameter 1: Jar file
#########################

:addtoclasspath
if not "%classpath%"=="" set classpath=%classpath%;
set classpath=%classpath%%1
goto :EOF