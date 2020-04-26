#gagar work in progress
#Containerize api-store through maven with jib, automatically publishes to your docker hub account
#Script is not runnable, replace the value between curly brackets and execute at the root of the project ;)
#you need to login to docker hub through cli to launch

./mvnw com.google.cloud.tools:jib-maven-plugin:build -Dimage={Docker hub username}/api-store