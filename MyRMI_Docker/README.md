# MyRMI_Docker

cmd used:

* `docker build -t [image_name] -f [Dockerfile_name] .`
    * `image_name`: e.g. imageregistry
    * `Dockerfile_name`: e.g. Dockerfile
* `docker run -it --rm --name [app_name] [image_name]`
    * `app_name`: e.g. app-registry
    * `-d`: run in background, check by `docker ps`, stop by `docker stop`
    * real: `docker run -itd --rm --name app-registry rock3yu/cs328_a4_registry:v-0.1`
    * real: `docker run -it --rm --name app-server rock3yu/cs328_a4_server:v-0.1`



