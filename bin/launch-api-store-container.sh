#gagar work in progress
#launch the api-store container
#Script is not runnable, replace the value between curly brackets and execute at the root of the project ;)
#you need to login to docker hub through cli to launch

docker run --rm --name api-store -p 8080:8080 -e API_STORE_PG_URL={postgres url and port} {docker-hub-username}/api-store