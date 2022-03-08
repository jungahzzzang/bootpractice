#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo ">전환할Port: $IDLE_PORT"
    echo ">Port 전환"
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc
        # 엔진엑스가 변경할 프록시 주소 생성                       # 앞에서 넘겨준 문장을 service-url.inc에 덮어씀.
    echo ">엔진엑스 Reload"
    sudo service nginx reload
}