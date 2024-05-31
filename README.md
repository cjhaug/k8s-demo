# Prerequisites

## Ubuntu wsl distro
Per default wsl stores images of an installed distribution in users app folder. If user is running out of disk space a distribution can be moved to a different location.

Following commands should be executed in a PowerShell as Administrator.

List installed distributions:
```shell
wsl --list --verbose
```
To move a distribution to a different location use following commands (adapt name of distribution and folders):
```shell
cd d:\_appdata\my_wsl_distros\
wsl --terminate Ubuntu-22.04
wsl --export Ubuntu-22.04 .\Ubuntu-22.04.tar
wsl --unregister Ubuntu-22.04
wsl --import Ubuntu-22.04 .\Ubuntu-22.04 .\Ubuntu-22.04.tar
wsl --set-version Ubuntu-22.04 2
wsl --set-default Ubuntu-22.04
```

Start wsl distribution. To define default user add an entry to `/etc/wsl.conf`:
```
[user]
default=<your_username>
```
## Install minikube
To install docker, minikube, kubectl and helm on WSL2 ubuntu follow [this guide](https://gist.github.com/wholroyd/748e09ca0b78897750791172b2abb051).

Repositories to install kubectl have been moved. Use following guide on [how to migrate repositories](https://kubernetes.io/blog/2023/08/15/pkgs-k8s-io-introduction/#how-to-migrate).

# Local kubernetes using minikube
Open terminal using WSL Ubuntu.

## Start minikube
```shell
minikube start
```

## Build application
Execute ```mvn clean package``` to build jar file.
Deployment of docker image is done via minikube hosted on WSL Ubuntu.
Because we are using internal repository to pull image we need to ```minikube ssh``` and build image.

### Mount filesystem
```shell
minikube mount /mnt/d/projects/repositories/varna-2024/k8s-demo:/development
```

### Build docker image
```shell
minikube ssh
cd /development/app
docker build --file=Dockerfile --tag=k8s-demo:latest --rm=true .
```

## Deploy application
```shell
kubectl apply -f kubernetes/app/deployment.yml
```

## Test application
Use minikube service to open a tunnel and access application deployed on minikube. 
```shell
kubectl get services
minikube service k8s-demo
```
Open additional bash and run ```minikube dashboard``` to inspect pod and logs.

# Install Prometheus
Open additional Ubuntu bash to install prometheus.
```shell
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm install -f kubernetes/app/prometheus.yml prometheus prometheus-community/prometheus
kubectl expose service prometheus-server --type=NodePort --target-port=9090 --name=prometheus-server-np
```

Use minikube service to open a tunnel and access prometheus server.
```shell
minikube service prometheus-server-np
```

# Install Grafana
Open additional Ubuntu bash to install grafana.
```shell
helm repo add grafana https://grafana.github.io/helm-charts
helm install -f kubernetes/app/grafana.yml grafana grafana/grafana
kubectl expose service grafana --type=NodePort --target-port=3000 --name=grafana-np
```
Use minikube service to open a tunnel and access grafana server.
```shell
kubectl get secret --namespace default grafana -o jsonpath="{.data.admin-password}" | base64 --decode ; echo
minikube service grafana-np
```
