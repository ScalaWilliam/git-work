#!/usr/bin/env bash

## Usage:
##   git-watch -i -- ./ansible/prod-deploy.sh

git pull && \
    sbt "show universal:packageZipTarball" && \
    cp /home/build/git-work/web/target/universal/git-work-0.1-SNAPSHOT.tgz ~/www/ && \
    cd ansible && \
    time ansible-playbook \
        -i git.work, \
        --extra-vars "web_hostname=git.work" \
        --tags deploy \
        --skip-tags install \
        prod-instance.yml