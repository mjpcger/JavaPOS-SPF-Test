#!/bin/sh
function error() {
    echo -n $1
    read q
    exit 1
}

test "$JAVA_HOME" = "" && error "JAVA_HOME not set. Java runtime must be installed first"
test -d jar -a -f jar/JavaPOS-SPF-Test.jar || error "Sub-folder jar does not exist"
if [ "$JFX_HOME" = "" ]
then
  test "$(find $JAVA_HOME -name javafx-swt.jar)" = "" -o "$(find $JAVA_HOME -name jfxrt.jar)" = "" && \
    error "JFX_HOME (path to current JavaFX installation) not set."
  VM_Flags=""
else
  if java --help | grep -e "--module-path" -q
  then
	  VM_Flags="--module-path $JFX_HOME/lib --add-modules=javafx.controls,javafx.fxml"
  else
	  VM_Flags=""
  fi
fi
classpath=""
for i in $(pwd)/jar/*.jar
do
	if [ "$classpath" = "" ]
	then
		classpath=$i
	else
		classpath="$classpath:$i"
	fi
done
echo "java $VM_Flags -cp $classpath SPF_Test.Main"
java $VM_Flags -cp $classpath SPF_Test.Main || error "Press Enter to continue"
