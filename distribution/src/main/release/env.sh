OSGI=1.4.0
FELIX=2.0.1
SCRIBBLE=1.0-SNAPSHOT

CLASSPATH=lib/org.apache.felix.framework-$OSGI.jar
CLASSPATH=$CLASSPATH:lib/org.apache.felix.main-$FELIX.jar
CLASSPATH=$CLASSPATH:lib/org.osgi.core-$OSGI.jar
CLASSPATH=$CLASSPATH:bundle/org.scribble.command-$SCRIBBLE.jar
CLASSPATH=$CLASSPATH:lib/org.scribble.commandline-$SCRIBBLE.jar

export CLASSPATH
