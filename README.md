RPC-Simple

从0到1完成一个RPC框架，包括服务调用、序列化、网络传输等等。

## 1. RPC原理
![img.png](images/imgyl.png)
![img.png](images/img56.png)

1.服务消费端（client）以本地调用的方式调用远程服务；

2.客户端 Stub（client stub） 接收到调用后负责将方法、参数等组装成能够进行网络传输的消息体（序列化）：`RpcRequest`；

3.客户端 Stub（client stub） 找到远程服务的地址，并将消息发送到服务提供端；

4.服务端 Stub（桩）收到消息将消息反序列化为 Java 对象: `RpcRequest`；

5.服务端 Stub（桩）根据RpcRequest中的类、方法、方法参数等信息调用本地的方法；

6.服务端 Stub（桩）得到方法执行结果并将组装成能够进行网络传输的消息体：`RpcResponse`（序列化）发送至消费方；

7.客户端 Stub（client stub）接收到消息并将消息反序列化为 Java 对象:`RpcResponse` ，这样也就得到了最终结果。over!

## 2. 整体架构
![img_1.png](images/img_jg.png)

## 3.文件结构
```
├─.idea
├─images
├─rpc-client
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │  └─com
│  │  │  │      └─barry
│  │  │  │          └─rpc
│  │  │  │              └─client
│  │  │  └─resources
│  │  └─test
│  │      └─java
│  └─target
│      ├─classes
│      │  └─com
│      │      └─barry
│      │          └─rpc
│      │              └─client
│      └─generated-sources
│          └─annotations
├─rpc-codec
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │  └─com
│  │  │  │      └─barry
│  │  │  │          └─rpc
│  │  │  │              └─codec
│  │  │  └─resources
│  │  └─test
│  │      └─java
│  │          └─com
│  │              └─barry
│  │                  └─rpc
│  │                      └─codec
│  └─target
│      ├─classes
│      │  └─com
│      │      └─barry
│      │          └─rpc
│      │              └─codec
│      ├─generated-sources
│      │  └─annotations
│      ├─generated-test-sources
│      │  └─test-annotations
│      └─test-classes
│          └─com
│              └─barry
│                  └─rpc
│                      └─codec
├─rpc-common
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │  └─com
│  │  │  │      └─barry
│  │  │  │          └─rpc
│  │  │  │              └─common
│  │  │  │                  └─utils
│  │  │  └─resources
│  │  └─test
│  │      └─java
│  │          └─com
│  │              └─barry
│  │                  └─rpc
│  │                      └─common
│  │                          └─utils
│  └─target
│      ├─classes
│      │  └─com
│      │      └─barry
│      │          └─rpc
│      │              └─common
│      │                  └─utils
│      ├─generated-sources
│      │  └─annotations
│      ├─generated-test-sources
│      │  └─test-annotations
│      └─test-classes
│          └─com
│              └─barry
│                  └─rpc
│                      └─common
│                          └─utils
├─rpc-example
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │  └─com
│  │  │  │      └─barry
│  │  │  │          └─rpc
│  │  │  │              └─example
│  │  │  └─resources
│  │  └─test
│  │      └─java
│  └─target
│      ├─classes
│      │  └─com
│      │      └─barry
│      │          └─rpc
│      │              └─example
│      └─generated-sources
│          └─annotations
├─rpc-proto
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │  └─com
│  │  │  │      └─barry
│  │  │  │          └─rpc
│  │  │  └─resources
│  │  └─test
│  │      └─java
│  └─target
│      ├─classes
│      │  └─com
│      │      └─barry
│      │          └─rpc
│      └─generated-sources
│          └─annotations
├─rpc-server
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │  └─com
│  │  │  │      └─barry
│  │  │  │          └─rpc
│  │  │  │              └─server
│  │  │  └─resources
│  │  └─test
│  │      └─java
│  │          └─com
│  │              └─barry
│  │                  └─rpc
│  │                      └─server
│  └─target
│      ├─classes
│      │  └─com
│      │      └─barry
│      │          └─rpc
│      │              └─server
│      ├─generated-sources
│      │  └─annotations
│      ├─generated-test-sources
│      │  └─test-annotations
│      └─test-classes
│          └─com
│              └─barry
│                  └─rpc
│                      └─server
└─rpc-transport
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─barry
│  │  │          └─rpc
│  │  │              └─transport
│  │  └─resources
│  └─test
│      └─java
└─target
├─classes
│  └─com
│      └─barry
│          └─rpc
│              └─transport
└─generated-sources
└─annotations

```

## 4. 实现结果
测试代码

```java
public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer(new RpcServerConfig());
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
```

```java
public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);

        int add = service.add(1, 2);
        int minus = service.minus(10, 8);
        System.out.println(add);
        System.out.println(minus);
    }
}
```

测试结果
![img.png](images/img.png)
![img_1.png](images/img_1.png)
