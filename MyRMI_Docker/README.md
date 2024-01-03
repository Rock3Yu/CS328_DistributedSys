# MyRMI_Docker

cmd used:
* `docker build -t [image_name] -f [Dockerfile_name] .`
    * `image_name`: e.g. imageregistry
    * `Dockerfile_name`: e.g. Dockerfile
*  `docker run -it --rm --name [app_name] [image_name]`
    * `app_name`: e.g. app-registry
    * `-d`: run in background, check by `docker ps`, stop by `docker stop`



