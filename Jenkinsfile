import groovy.json.JsonSlurperClassic

pipeline {
    agent any

    environment {
        INSTANCE_ID = ""
        HOST_NAME = ""
        SSH_HOST = ""
        LIB_PATH = ""
        CURRENT_STAGE = ""
        EUREKA_PORT = ""
        BACKEND_TARGET = "/data/tea_dev"
        VERSION_NAME = "1.0.0-SNAPSHOT"
        JAVA_OPTS="-Xmx256m"
        JENKINS_NODE_COOKIE = 'dontKillMe'
    }

    stages {
        stage("env") {
            steps {
                script {
                    if (env.Target == 'EC2') {
                        INSTANCE_ID = "dev"
                        EUREKA_PORT= "1026"
                        HOST_NAME = "47.99.199.86"
                        SSH_HOST = "ubuntu@${HOST_NAME}"
                    } else if (env.Target == 'SCRCU') {
                        INSTANCE_ID = "prd"
                        EUREKA_PORT= "1025"
                        HOST_NAME = "47.99.199.86"
                        SSH_HOST = "root@${HOST_NAME}"
                    } else {
                        INSTANCE_ID = "dev"
                        EUREKA_PORT= "1026"
                        HOST_NAME = "47.99.199.86"
                        SSH_HOST = "root@${HOST_NAME}"
                    }
                }
            }
        }

        stage("Build") {
            steps {
                script {
                    sh "mvn install; cd .."
                }
            }
        }
           stage("demand") {
            steps {
                script {
                    /*current state name*/
                    CURRENT_STAGE = "dcmd-service-demand"
                    LIB_PATH = "${BACKEND_TARGET}/${INSTANCE_ID}/${CURRENT_STAGE}/lib"
                    MainClass = "com.dcmd.service.demand.DemandServiceApplication"

                    prePare()
                    stopService()
                    startService()
                    checkService()
                }
            }
        }
    }
}

void prePare() {
    sh "ssh ${SSH_HOST} 'mkdir -p ${LIB_PATH}'"
    /*mkdir logs*/
    sh "ssh ${SSH_HOST} 'mkdir -p ${BACKEND_TARGET}/${INSTANCE_ID}/${CURRENT_STAGE}/logs'"
}

void stopService() {
    def creds = "dcit:dcit"
    String auth = creds.bytes.encodeBase64().toString()

    /*get instance registration info*/
    def response = httpRequest url: "http://${HOST_NAME}:${EUREKA_PORT}/eureka/apps/${CURRENT_STAGE}",
                   acceptType: "APPLICATION_JSON",
                   contentType: "APPLICATION_JSON",
                   customHeaders: [[name: 'Authorization', value: "Basic ${auth}"]],
                   validResponseCodes: '200:404',
                   consoleLogResponseBody: false

    if ( response.status == 200 ) {
        def data = new JsonSlurperClassic().parseText(response.content)
        if ( data.application.instance.status[0] == "UP" ) {
            /*get instance port*/
            def instancePort = data.application.instance.port.$[0]
            /*shutdown*/
            def shutdown = httpRequest url: "http://${HOST_NAME}:${instancePort}/actuator/shutdown",
                           httpMode: "POST",
                           acceptType: "APPLICATION_JSON",
                           contentType: "APPLICATION_JSON",
                           validResponseCodes: '200:404',
                           consoleLogResponseBody: true
            /*wait eureka refresh api cache*/
            //sleep 5
        } else {
            echo "status: ${data.application.instance.status[0]}"
        }
    } else {
        echo "response: ${response.status}, service not found"
    }
}

void startService() {

    /*copy jar*/
    sh "rsync -au ./build/${CURRENT_STAGE}-${VERSION_NAME}.jar ${SSH_HOST}:${BACKEND_TARGET}/${INSTANCE_ID}/${CURRENT_STAGE}/"
    /*copy resources*/
    sh "rsync -au ./src/main/resources/* ${SSH_HOST}:${BACKEND_TARGET}/${INSTANCE_ID}/${CURRENT_STAGE}/"
    /*copy run lib*/
    sh "rsync -au ./build/lib/* ${SSH_HOST}:${LIB_PATH}/"
    sh "ssh ${SSH_HOST} 'cd ${BACKEND_TARGET}/${INSTANCE_ID}/${CURRENT_STAGE}; chmod 755 *.jar; cd lib; chmod 755 *.jar'"

    /*run*/
    /*sh """
       ssh ${SSH_HOST} 'cd ${BACKEND_TARGET}/${INSTANCE_ID}/${CURRENT_STAGE}; nohup java $JAVA_OPTS -Dloader.path=.,${LIB_PATH} -jar ${CURRENT_STAGE}-${VERSION_NAME}.jar --spring.profiles.active=${INSTANCE_ID} > /dev/null 2>&1 &'
    """*/
    sh """
       ssh ${SSH_HOST} 'cd ${BACKEND_TARGET}/${INSTANCE_ID}/${CURRENT_STAGE}; nohup java $JAVA_OPTS -cp ${CURRENT_STAGE}-${VERSION_NAME}.jar:lib/* ${MainClass} --spring.profiles.active=${INSTANCE_ID} > /dev/null 2>&1 &'
    """
}

void checkService() {
    /*check eureka*/
    if ( "${CURRENT_STAGE}" == "dcit-eureka" ) {
        timeout(3) {
            waitUntil {
              script {
                def r = sh script: "wget -q http://${HOST_NAME}:${EUREKA_PORT}/info -O /dev/null", returnStatus: true
                return (r == 0)
                sleep 1
              }
           }
        }
    } else {
        def creds = "dcit:dcit"
        String auth = creds.bytes.encodeBase64().toString()

        timeout(5) {
            waitUntil {
              script {

                def response = httpRequest url: "http://${HOST_NAME}:${EUREKA_PORT}/eureka/apps/${CURRENT_STAGE}",
                       acceptType: "APPLICATION_JSON", 
                       contentType: "APPLICATION_JSON",
                       customHeaders: [[name: 'Authorization', value: "Basic ${auth}"]],
                       validResponseCodes: '200:404',
                       consoleLogResponseBody: false
                
                if ( response.status == 200 ) {
                    def data = new JsonSlurperClassic().parseText(response.content)
                    try {
                        if ( data.application.instance.status[0] == "UP" ) {
                            return true
                        } else {
                            echo "status: ${data.application.instance.status[0]}"
                            return false
                        }
                    } catch (e) {
                        echo "status exception"
                        return false
                    }
                } else {
                    echo "response: ${response.status}"
                    sleep 1
                    return false
                }
              }
           }
        }
    }
}
