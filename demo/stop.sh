#!/bin/bash

#PLATFORM=$(pwd)/../demo


#cd "$PLATFORM"
docker-compose down

# remove <none> images
docker image prune