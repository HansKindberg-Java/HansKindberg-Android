REM The org.sonatype.oss:oss-parent declares org.apache.maven.plugins:maven-gpg-plugin:1.1
REM Running the Maven build "install" from inside IntelliJ hangs at:
REM maven-gpg-plugin:1.1:sign (sign-artifacts)
REM GPG Passphrase:
REM So instead om running Maven build "install" from inside IntelliJ, run this command file (Windows)
REM The plugin installs the artifacts in the local Maven repository

mvn install -e
PAUSE