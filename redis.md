## 时代背景

### 1. Web1.0时代

Web1.0的时代，数据访问量很有限，用一夫当关的高性能的单点服务器可以解决大部分问题。

![img](E:\redis6笔记\images\wps1.jpg)





### 2. Web2.0时代

随着Web2.0的时代的到来，用户访问量大幅度提升，同时产生了大量的用户数据。加上后来的智能移动设备的普及，所有的互联网平台都面临了巨大的性能挑战。

![img](E:\redis6笔记\images\wps2.jpg)



### 3. 解决CPU及内存压力

**问题**：分布式服务器session对象不一致，共享问题。

1.session存储到客户端cookie（不安全、网络负担效率低）

2.session复制到各个分布式服务器（数据冗余）

3.**存储到NoSQL数据库（完全在内存中，速度快）**

![img](file:///C:\Windows\TEMP\ksohtml10524\wps3.png) 

### 4. 解决IO压力

**NoSQL数据库可以解决IO压力**，将查询频繁的数据缓存到NoSQL数据库

![img](file:///C:\Windows\TEMP\ksohtml10524\wps4.png)





## NoSQL

NoSQL(NoSQL = Not Only SQL )，意即“不仅仅是SQL”，泛指[非关系型数据库](https://so.csdn.net/so/search?q=非关系型数据库&spm=1001.2101.3001.7020)。
[关系型数据库和非关系型数据库的详细分系](https://blog.csdn.net/weixin_47872288/article/details/121917162?spm=1001.2014.3001.5501)

### 1. NoSQL特点

- 非关系型数据库，不依赖业务逻辑数据库存储，以简单key-value存储。因此大大的增加了数据库的扩展能力
- 不遵循SQL标准
- 不支持ACID （原子性 一致性 隔离性 持久性）
- 远超于SQL的性能。



### 2. 适用场景

- 对数据高并发的读写。

- 海量数据的读写。

- 对数据高可扩展性的。

### 3. 不适用场景

-  需要事务支持
-  基于sql的结构化查询存储，处理复杂的关系,需要即席查询。
- **（用不着sql的和用了sql也不行的情况，请考虑用NoSql）**



### 4. NoSQL优点

- 缓存数据库，完全在内存中，速度快，数据结构简单
- 减少io操作，数据库和表拆分，虽然破坏业务逻辑，即外加一个缓存数据库，提高数据库速度，也可以用专门的存储方式，以及针对不同的数据结构存储



### 5. 常见NoSql数据库

| 数据库   | 功能                                                         |
| :------- | :----------------------------------------------------------- |
| Memcache | ① 很早出现的NoSql数据库                                                                                      ② 数据都在内存中，一般**不持久化**                                                                        ③ key-value模式，**支持类型单一**（字符串）                                                                                     ④ 一般是作为**缓存数据库**辅助持久化的数据库 |
| Redis    | ① 几乎覆盖了Memcached的绝大部分功能                                                                                      ② 数据都在内存中，**支持持久化**，主要用作备份恢复                                                                           ③ 除了支持简单的key-value模式，还**支持多种数据结构的存储**，比如 字符串、list、set、hash、zset等                                            ④ 一般是作为**缓存数据库**辅助持久化的数据库 |
| MongoDB  | ① 高性能、开源、模式自由(schema free)的**文档型数据库**                                                                   ② 数据都在内存中， 如果内存不足，把不常用的数据保存到硬盘                                                                 ③ 虽然是key-value模式，但是对value（尤其是**json**）提供了丰富的查询功能                                                  ④ **支持二进制数据及大型对象**                                                                                                 ⑤ 可以根据数据的特点**替代RDBMS** ，成为独立的数据库。或者配合RDBMS，存储特定的数据。 |
|          |                                                              |

### 6. 其它NoSql数据库

​	

#### 1) 行式存储数据库（大数据时代）



**行式数据库**

![img](file:///C:\Windows\TEMP\ksohtml10524\wps5.png) 

**列式数据库**

![img](file:///C:\Windows\TEMP\ksohtml10524\wps6.png)



##### (1) Hbase数据库

![1674808077038](E:\redis6笔记\images\1674808077038.png)

HBase是**Hadoop**项目中的数据库。它用于需要对大量的数据进行随机、实时的读写操作的场景中。

HBase的目标就是处理数据量**非常庞大**的表，可以用**普通的计算机**处理超过**10亿行数据**，还可处理有数百万**列**元素的数据表



##### (2) Cassandra数据库

![1674808034580](E:\redis6笔记\images\1674808034580.png)

Apache Cassandra是一款免费的开源NoSQL数据库，其设计目的在于管理由大量商用服务器构建起来的庞大集群上的**海量数据集(数据量通常达到PB级别)**。在众多显著特性当中，Cassandra最为卓越的长处是对写入及读取操作进行规模调整，而且其不强调主集群的设计思路能够以相对直观的方式简化各集群的创建与扩展流程。



#### 2) 图关系型数据库

**Neo4j数据库**

![1674808153523](E:\redis6笔记\images\1674808153523.png)

主要应用：社会关系，公共交通网络，地图及网络拓谱(n*(n-1)/2)

![img](E:\redis6笔记\images\wps7.png)









## Redis



几乎覆盖了Memcached的绝大部分功能数据都在内存中，支持持久化，主要用作备份恢复除了支持简单的key-value模式，还支持多种数据结构的存储，比如list、set、 hash、 zset等。一般是作为缓存数据库辅助持久化的数据库。



### 1. Redis 概述



#### 1) Redis介绍

- Redis 是一个开源的 key-value 存储系统。
- 和 Memcached 类似，它支持存储的 value 类型相对更多，包括 string (字符串)、list (链表)、set (集合)、zset (sorted set –有序集合) 和 hash（哈希类型）。
- 这些数据类型都支持 push/pop、add/remove 及取交集并集和差集及更丰富的操作，而且这些操作都是原子性的。
- 在此基础上，Redis 支持各种不同方式的排序。
- 与 memcached 一样，为了保证效率，数据都是缓存在内存中。
- 区别的是 Redis 会周期性的把更新的数据写入磁盘或者把修改操作写入追加的记录文件。
- 并且在此基础上实现了 master-slave (主从) 同步。



#### 2) 应用场景

(1) 配合关系型数据库做高速缓存

- 高频次，热门访问的数据，降低数据库 IO。
- 分布式架构，做 session 共享。

![1674806692481](E:\redis6笔记\images\1674806692481.png)



(2) 多样的数据结构存储持久化数据

![1674806745462](E:\redis6笔记\images\1674806745462.png)



#### 3) redis特点

1. 单线程+多路IO复用
2. 支持多种数据类型
3. 支持持久化



#### 4) 相关技术

![1675163842896](E:\redis6笔记\images\1675163842896.png)

**Redis 使用的是单线程 + 多路 IO 复用技术：**

多路复用是指使用一个线程来检查多个文件描述符（Socket）的就绪状态，比如调用 select 和 poll 函数，传入多个文件描述符，如果有一个文件描述符就绪，则返回，否则阻塞直到超时。得到就绪状态后进行真正的操作可以在同一个线程里执行，也可以启动线程执行（比如使用线程池）。

**串行 vs 多线程 + 锁（memcached） vs 单线程 + 多路 IO 复用 (Redis)**

(与 Memcache 三点不同：**支持多数据类型，支持持久化，单线程 + 多路 IO 复用**)

![1675163694116](E:\redis6笔记\images\1675163694116.png)





### 2. redis安装



| Redis官方网站   | Redis中文官方网站 |
| --------------- | ----------------- |
| http://redis.io | http://redis.cn/  |
|                 |                   |

#### 1) 安装版本

- 6.2.1 for Linux（redis-6.2.1.tar.gz）

- 不用考虑在windows环境下对Redis的支持

	![1675162943223](E:\redis6笔记\images\1675162943223.png)



#### 2) 安装步骤



(1) 准备工作：下载安装最新版的gcc编译器 【CenOS7自带】



安装 C语言的编译环境 

- yum install centos-release-scl scl-utils-build

- yum install -y devtoolset-8-toolchain

- scl enable devtoolset-8 bash

	

测试 gcc版本 

​	`gcc --version`



(2) 下载redis-7.0.8.tar.gz 放 /opt目录

(3) 解压命令：tar -zxvf redis-7.0.8.tar.gz

(4) 解压完成后进入目录：cd redis-7.0.8

(5) 在 redis-6.2.1 目录下再次执行make命令（只是编译好）

​	如果没有准备好C语言编译环境，make 会报错—Jemalloc/jemalloc.h：没有那个文件

​    解决方案：运行make distclean

(8) 在 redis-7.0.8 目录下再次执行make命令（只是编译好）

(9) 跳过 make test 继续执行: make install 



#### 3) 安装目录

安装目录 /usr/local/bin

查看默认安装目录：

redis-benchmark: 性能测试工具，可以在自己本子运行，看看自己本子性能如何

redis-check-aof：修复有问题的AOF文件，rdb和aof后面讲

redis-check-dump：修复有问题的dump.rdb文件

redis-sentinel：Redis集群使用

redis-server：Redis服务器启动命令

redis-cli：客户端，操作入口



#### 4) 前台启动（不推荐）

前台启动，命令行窗口不能关闭，否则服务器停止

`redis-server`

![1675224652400](E:\redis6笔记\images\1675224652400.png)



#### 5) 后台启动（推荐）



(1) 备份redis.conf

​	拷贝一份redis.conf到其他目录

​	`cp  /opt/redis-7.0.8/redis.conf  /etc/redis.conf`

​	

(2) 后台启动设置 daemonize no 改成 yes

​	修改 /etc 目录下的 redis.conf 文件将里面的 daemonize no 改成 yes，让服务在后台启动

​	![1675229071540](E:\redis6笔记\images\1675229071540.png)



(3) Redis启动

​	到安装目录 /usr/local/bin 下 执行`redis-server /etc/redis.conf`

​	使用命令查看 `ps -ef | grep redis`

​	![1675230386254](E:\redis6笔记\images\1675230386254.png)



(4) 用客户端访问：redis-cli

​	![1675229825726](E:\redis6笔记\images\1675229825726.png)

(5) 多个端口可以：redis-cli -p6379

​	

(6) 测试验证：ping

​	![1675229874587](E:\redis6笔记\images\1675229874587.png)

(7) Redis关闭



- 单实例关闭：`redis-cli shutdown`

​	![1675230140924](E:\redis6笔记\images\1675230140924.png)



- 也可以进入终端后再关闭：`redis-cli` `shutdowm`

​	![1675230296395](E:\redis6笔记\images\1675230296395.png)

 	

- 多实例关闭，指定端口关闭：`redis-cli -p 6379 shutdown`





### 3. 常用5大数据结构

#### 0) Redis 键(key)



`keys *` 查看当前库所有key    (匹配：keys *1)

 ![1675231599920](E:\redis6笔记\images\1675231599920.png)



`exists key` 判断某个key是否存在 （1代表存在，0代表不存在）

 ![1675231800756](E:\redis6笔记\images\1675231800756.png)



`type key` 查看你的key是什么类型

 ![1675231829149](E:\redis6笔记\images\1675231829149.png)



`del key` 删除指定的key数据（1代表已删除 0代表未删除）

 ![1675231872713](E:\redis6笔记\images\1675231872713.png)



`unlink key` 根据value选择非阻塞删除（仅将 keys 从 keyspace元数据中删除，真正的删除会在后续异步操作）



`expire key 秒数`：为给定的key设置过期时间

 ![1675232049627](E:\redis6笔记\images\1675232049627.png)



`ttl key` 查看还有多少秒过期，-1表示永不过期，-2表示已过期

 ![1675232172679](E:\redis6笔记\images\1675232172679.png)



`select 数据库号` 切换数据库

 ![1675232295186](E:\redis6笔记\images\1675232295186.png)



`dbsize` 查看当前数据库的key的数量

 ![1675232341982](E:\redis6笔记\images\1675232341982.png)



`flushdb` 清空当前库

`flushall` 通杀全部库

#### 1) String字符串

- String是Redis最基本的类型，你可以理解成与Memcached 一模一样的类型，一个key 对应一个value。
- String类型是二进制安全的。 意味着Redis的string 可以包含任何数据。比如 jpg图片 或者 序列化的对象。

- 底层数据结构为**动态字符串**
- String类型是Redis最基本的数据类型，一个Redis中字符串value最多可以是512M



##### (1) 常用命令



取值：`get <key>`

设置key的值：`set <key> <value>`

设置key的值（key不存在时生效）：`setnx <key> <value>` 

设置key的值，并设置过期时间(s)：`setex  <key> <过期时间> <value>`



同时获取多个值：`mget <key> <key> <key> ...`

同时设置一个或多个 key-value对：`mset  <key1> <value1> <key2> <value2>  ...`

同时设置一个或多个 key-value对（所有key不存在时生效）：`msetnx <key> <value> <key> <value> ...` 

**原子性，有一个失败则都失败**



取到旧值，被新值替换：`getset <key> <value>`

以新换旧，设置了新值同时获得旧值：`getset <key> <value>`

指定位置插入值，从<起始索引>开始 ：`setrange  <key> <起始索引> <value>`



追加 value：`append <key> <value>`

获取值长度：`strlen(<key>)`

范围查询(类似java中的substring，前包，后包)：`getrange <key> <起始位置> <结束位置>`



将 key 中储存的数字值增1：`incr  <key>`

（只能对数字值操作，如果为空，新增值为1）（原子操作（不会被线程调度机制打断））

将 key 中储存的数字值减1：`decr  <key>`

（只能对数字值操作，如果为空，新增值为-1）（原子操作）

将 key 中储存的数字值 增减（自定义步长）：`incrby / decrby  <key><步长>`

（原子操作）



##### (2) 原子性

所谓原子操作是指不会被线程调度机制打断的操作；

这种操作一旦开始，就一直运行到结束，中间不会有任何 context switch （切换到另一个线程）。

（1）在单线程中， 能够在单条指令中完成的操作都可以认为是"原子操作"，因为中断只能发生于指令之间。

（2）在多线程中，不能被其它进程（线程）打断的操作就叫原子操作。

​	Redis单命令的原子性主要得益于Redis的单线程。



关于原子性我们来分析一个问题：

![1675235997936](E:\redis6笔记\images\1675235997936.png)

i++是一个 **取值、加一、赋值** 的操作，如果两个线程 分别对 i ++100次

完了之后 i最大的情况是，当线程a 对i ++完100次之后， 线程b在开启 并取这个值 ++100次，结果为200

i最小的情况是，线程a 对i 取值的时候 线程b 会打断 线程a的执行，就比如 线程a 中i已经是 99了，此时线程a想要 取这个99在加1

但是被线程b打断了，线程b的i为 0 然后加1 变成1，后线程a 打断 继续执行 但是此时取到的 i为1，所以结束之后 结果为2  



##### (3) 数据结构

String的数据结构为简单动态字符串(Simple Dynamic String,缩写SDS)。是可以修改的字符串，内部结构实现上类似于Java的ArrayList，采用预分配冗余空间的方式来减少内存的频繁分配.

![1675237282609](E:\redis6笔记\images\1675237282609.png)





#### 2) List链表

单键多值

Redis列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）。

它的底层实际是个**双向链表**，对两端的操作性能很高，通过索引下标的操作中间的节点性能会较差。

![1675242000999](E:\redis6笔记\images\1675242000999.png)

Redis将链表和ziplist结合起来组成了quicklist。 也就是将多个ziplist使用双向指针串起来使用。这样既满足了快速的插入删除性能，又不会出现太大的空间冗余。



##### 1) 常用命令



从左边/右边插入一个或多个值：`lpush/rpush <key> <value1> <value2> <value3> ... `

从左边/右边吐出一个值（值在键在，值光键亡）：`lpop/rpop <key>`

从 key1列表右边吐出一个值，插到 key2列表左边：`rpoplpush  <key1> <key2>`

按照索引下标获得元素(从左到右)：`lrange <key><start><stop>`

获取（0左边第一个，-1右边第一个，0-1表示获取所有）：`lrange mylist 0 -1 `

按照索引下标获得元素(从左到右)：`lindex <key><index>`

获得列表长度：`llen <key>`



在value的后面插入newvalue插入值：`linsert <key>  before <value> <newvalue>`

从左边删除n个value(从左到右)：`lrem <key> <n> <value>`

将列表key下标为index的值替换成value：`lset <key> <index> <value>`





##### (2) 数据结构

List的数据结构为快速链表quickList。

首先在列表元素较少的情况下会使用一块连续的内存存储，这个结构是ziplist，也即是压缩列表。

压缩列表将所有的元素紧挨着一起存储，分配的是一块连续的内存。当数据量比较多的时候才会改成quicklist。

因为普通的链表需要的附加指针空间太大，会比较浪费空间。比如这个列表里存的只是int类型的数据，结构上还需要两个额外的指针prev和next。

![1675242097037](E:\redis6笔记\images\1675242097037.png)

Redis将链表和ziplist结合起来组成了quicklist。也就是将多个ziplist使用双向指针串起来使用。这样既满足了快速的插入删除性能，又不会出现太大的空间冗余。



#### 3) Set集合

Redis set对外提供的功能与list类似是一个列表的功能，特殊之处在于set是可以**自动排重**的，当你需要存储一个列表数据，又不希望出现重复数据时，set是一个很好的选择，并且set提供了判断某个成员是否在一个set集合内的重要接口，这个也是list所不能提供的。

Redis的Set是string类型的无序集合。它**底层其实是一个value为null的hash表**，所以添加，删除，查找的**复杂度都是O(1)**。

一个算法，随着数据的增加，执行时间的长短，如果是O(1)，数据增加，查找数据的时间不变



##### (1) 常用命令

将一个或多个 member元素加入到集合 key中（已经存在的 member元素将被忽略）：`sadd <key> <value1> <value2> ...` 

取出该集合的所有值：`smembers <key>`

判断集合key是否为含有该value值：`sismember <key> <value>`

集合的元素个数：`scard <key>`

删除集合中的某个元素：`srem <key> <value1> <value2> ....` 

随机从该集合中吐出一个值（会从集合中删除）：`spop <key>` 

随机从该集合中取出n个值（不会从集合中删除）：`srandmember <key> <n>` 

把集合中一个值从一个集合移动到另一个集合：`smove <source> <destination> value`

两个集合的交集元素：`sinter <key1> <key2>` 

两个集合的并集元素：`sunion <key1> <key2>` 

两个集合的差集元素(key1中的，不包含key2中的)：`sdiff <key1> <key2>` 





##### (2) 数据结构

Set数据结构是 dict字典，字典是用**哈希表**实现的。

Java中HashSet的内部实现使用的是HashMap，只不过所有的value都指向同一个对象。Redis的set结构也是一样，它的内部也使用hash结构，所有的value都指向同一个内部值。



#### 4) Hash哈希

Redis hash 是一个键值对集合。

Redis hash是一个 string类型的field 和 value 的映射表，hash特别适合用于存储对象。

类似Java里面的Map<String,Object>

用户ID为查找的key，存储的value用户对象包含姓名，年龄，生日等信息，如果用普通的key/value结构来存储



**主要有以下2种存储方式：**

**方式一：**

​	1001:{name="张三",age=18,birth="09-05"}

![1675242223793](E:\redis6笔记\images\1675242223793.png)

缺点：每次修改用户的某个属性需要，先反序列化改好后再序列化回去。开销较大。



**方式二：**

​	1001name:"张三"

​	1001age:18

​	1001brith:"09-05"

![1675242237769](E:\redis6笔记\images\1675242237769.png)

缺点：用户ID数据冗余



**解决方式**：

​			name 	"张三"

​	1001 :  age  	18

​			birth	"09-05"

![1675242255328](E:\redis6笔记\images\1675242255328.png)

通过 key(用户ID) + field(属性标签) 就可以操作对应属性数据了，既不需要重复存储数据，也不会带来序列化和并发修改控制的问题





##### (1) 常用命令

给 key 集合中的 field 键赋value：`hset <key> <field> <value>`

从 key1集合 field 取出 value ：`hget <key1> <field> `

批量设置hash的值：`hmset <key1> <field1> <value1> <field2> <value2>...` 

查看哈希表 key 中，给定域 field 是否存在：`hexists <key> <field>`

列出该hash集合的所有field：`hkeys <key>`

列出该hash集合的所有value：`hvals <key>`

为哈希表 key 中的域 field 的值加上增量 1  -1：`hincrby <key> <field> <increment>`

将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在 ：`hsetnx <key><field><value>`



##### (2) 数据结构

Hash类型对应的数据结构是两种：ziplist（压缩列表），hashtable（哈希表）。当field-value长度较短且个数较少时，使用ziplist，否则使用hashtable。	



#### 5) Zset 有序集合



Redis有序集合zset与普通集合set非常相似，是一个没有重复元素的字符串集合。

不同之处是有序集合的每个成员都关联了一个**评分（score）**,这个评分（score）被用来按照从最低分到最高分的方式排序集合中的成员。**集合的成员是唯一的，但评分可以是重复** 

因为元素是有序的, 所以你也可以很快的根据 评分（score）或者 次序（position）来获取一个范围的元素。

访问有序集合的中间元素也是非常快的,因此你能够使用有序集合作为一个没有重复成员的智能列表。



##### (1) 常用命令



将一个或多个 member 元素及其 score 值加入到有序集 key 当中

`zadd <key> <score1> <value1> <score2> <value2> ...`



返回有序集 key 中，下标在 start 、stop 之间的元素，带WITHSCORES，可以让分数一起和值返回到结果集。

`zrange <key> <start> <stop> [WITHSCORES]`   



返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按 score 值递增(从小到大)次序排列。 

`zrangebyscore key min max [withscores] [limit offset count]`



同上，改为从大到小排列

`zrevrangebyscore key max min [withscores] [limit offset count] `              



为元素的score加上增量

`zincrby <key> <increment> <value>`



删除该集合下，指定值的元素 

`zrem  <key> <value>`

统计该集合，分数区间内的元素个数 

`zcount <key> <min> <max>`

返回该值在集合中的排名，从0开始。

`zrank <key> <value>`



案例：如何利用zset实现一个文章访问量的排行榜？

`zrange <key> <start> <stop> [WITHSCORES]`

![1675244455580](E:\redis6笔记\images\1675244455580.png)





##### (2) 数据结构

SortedSet(zset)是Redis提供的一个非常特别的数据结构，一方面它等价于Java的数据结构Map<String, Double>，可以给每一个元素value赋予一个权重score，另一方面它又类似于TreeSet，内部的元素会按照权重score进行排序，可以得到每个元素的名次，还可以通过score的范围来获取元素的列表。

zset底层使用了两个数据结构

- hash，hash的作用就是关联元素value和权重score，保障元素value的唯一性，可以通过元素value找到相应的score值。

- 跳跃表，跳跃表的目的在于给元素value排序，根据score的范围获取元素列表。



##### (3) 跳跃表（跳表）

​	有序集合在生活中比较常见，例如根据成绩对学生排名，根据得分对玩家排名等。对于有序集合的底层实现，可以用数组、平衡树、链表等。数组不便元素的插入、删除；平衡树或红黑树虽然效率高但结构复杂；链表查询需要遍历所有效率低。Redis采用的是跳跃表。跳跃表效率堪比红黑树，实现远比红黑树简单。



**实例**

​	对比有序链表和跳跃表，从链表中查询出51

- 有序链表

   ![1675242740963](E:\redis6笔记\images\1675242740963.png)

​	要查找值为51的元素，需要从第一个元素开始依次查找、比较才能找到。共需要6次比较。

- 跳跃表

​	![1675242812671](E:\redis6笔记\images\1675242812671.png)

   从第2层开始，1节点比51节点小，向后比较。21节点比51节点小，继续向后比较，后面就是NULL了，所以从21节点向下到第1层

   在第1层，41节点比51节点小，继续向后，61节点比51节点大，所以从41向下

   在第0层，51节点为要查找的节点，节点被找到，共查找4次。



从此可以看出跳跃表比有序链表效率要高



### 4. 配置文件

自定义目录：/myredis/redis.conf



#### 1) Units单位

配置大小单位,开头定义了一些基本的度量单位，**只支持bytes，不支持bit**

大小写不敏感

![1675308388171](C:\Users\huach\Desktop\账号\图标\1675308388171.png)

#### 2) INCLUDES包含

类似jsp中的include，多实例的情况可以把公用的配置文件提取出来

![1675308454225](C:\Users\huach\Desktop\账号\图标\1675308454225.png)

#### 3) 网络相关配置

![1675311278553](C:\Users\huach\Desktop\账号\图标\1675311278553.png)

##### (1) bind

默认情况bind=127.0.0.1只能接受本机的访问请求

![1675311306757](C:\Users\huach\Desktop\账号\图标\1675311306757.png)

不写的情况下，无限制接受任何ip地址的访问

生产环境肯定要写你应用服务器的地址；服务器是需要远程访问的，所以需要将其注释掉

![1675311338583](C:\Users\huach\Desktop\账号\图标\1675311338583.png)

##### (2) protected-mode

如果开启了protected-mode，那么在没有设定bind ip且没有设密码的情况下，Redis只允许接受本机的响应

![1675312783293](C:\Users\huach\Desktop\账号\图标\1675312783293.png)

将本机访问保护模式设置no

![1675312884519](C:\Users\huach\Desktop\账号\图标\1675312884519.png)

保存配置，停止服务，重启启动查看进程，不再是本机访问了。

##### (3) Port

端口号，默认 6379

![1675313071120](C:\Users\huach\Desktop\账号\图标\1675313071120.png)



##### (4) tcp-backlog

设置 tcp 的backlog，**backlog其实是一个连接队列**，**backlog队列总和 = 未完成三次握手队列 + 已经完成三次握手队列**。

在高并发环境下你需要一个高backlog值来避免慢客户端连接问题。

![1675313185749](C:\Users\huach\Desktop\账号\图标\1675313185749.png)

注意：Linux内核会将这个值减小到 /proc/sys/net/core/somaxconn 的值（128），所以需要确认增大 /proc/sys/net/core/somaxconn和 /proc/sys/net/ipv4/tcp_max_syn_backlog（128）两个值来达到想要的效果

##### (5) timeout

一个空闲的客户端维持多少秒会关闭，0表示关闭该功能。即永不关闭。

![1675313258523](C:\Users\huach\Desktop\账号\图标\1675313258523.png)



##### (6) tcp-keepalive

对访问客户端的一种心跳检测，每个n秒检测一次。

单位为秒，如果设置为0，则不会进行Keepalive检测，建议设置成60 

![1675313520310](C:\Users\huach\Desktop\账号\图标\1675313520310.png)

通过这个检查，redis判断你有没有在操作，有在操作就继续提供服务 没有就释放链接 



#### 4) GENERAL通用

![1675313633742](C:\Users\huach\Desktop\账号\图标\1675313633742.png)

##### (1) daemonize

是否为后台进程，设置为yes

守护进程，后台启动

![1675313652799](C:\Users\huach\Desktop\账号\图标\1675313652799.png)



##### (2) pidfile

存放pid文件的位置，每个实例会产生一个不同的pid文件

![1675313729019](C:\Users\huach\Desktop\账号\图标\1675313729019.png)



##### (3) loglevel 

指定日志记录级别，Redis总共支持四个级别：**debug、verbose、notice、warning**，默认为**notice**

四个级别根据使用阶段来选择，生产环境选择notice 或者warning

![1675313752735](C:\Users\huach\Desktop\账号\图标\1675313752735.png)



(4) logfile 

日志文件名称

![1675313774090](C:\Users\huach\Desktop\账号\图标\1675313774090.png)



##### (5) databases 16

设定库的数量 默认16，默认数据库为0，可以使用SELECT <dbid> 命令在连接上指定数据库id

![1675313802355](C:\Users\huach\Desktop\账号\图标\1675313802355.png)



#### 5) SECURITY安全

![1675313990234](C:\Users\huach\Desktop\账号\图标\1675313990234.png)



##### (1) 设置密码

**① 将 注释的 requirepass foobared 打开**

![1675314137628](C:\Users\huach\Desktop\账号\图标\1675314137628.png)



**② 访问密码的查看、设置和取消**

在命令中设置密码，只是临时的。重启redis服务器，密码就还原了。

永久设置，需要再配置文件中进行设置。

![1675251090463](E:\redis6笔记\images\1675251090463.png)



#### 6) LIMITS限制



##### (1) maxclients

- 设置redis同时可以与多少个客户端进行连接。

- 默认情况下为10000个客户端。

- 如果达到了此限制，redis则会拒绝新的连接请求，并且向这些连接请求方发出“max number of clients reached”以作回应。

​	![1675314521775](C:\Users\huach\Desktop\账号\图标\1675314521775.png)



##### (2) maxmemory 

- 建议**必须设置**，否则，将内存占满，造成服务器宕机

- 设置redis可以使用的内存量。一旦到达内存使用上限，redis将会试图移除内部数据，移除规则可以通过maxmemory-policy来指定。

- 如果redis无法根据移除规则来移除内存中的数据，或者设置了“不允许移除”，那么redis则会针对那些需要申请内存的指令返回错误信息，比如SET、LPUSH等。

- 但是对于无内存申请的指令，仍然会正常响应，比如GET等。如果你的redis是主redis（说明你的redis有从redis），那么在设置内存使用上限时，需要在系统中留出一些内存空间给同步队列缓存，只有在你设置的是“不移除”的情况下，才不用考虑这个因素。

	![1675314586725](C:\Users\huach\Desktop\账号\图标\1675314586725.png)





##### (3) maxmemory-policy



- volatile-lru：使用LRU算法移除key，只对设置了过期时间的键；（最近最少使用）

- allkeys-lru：在所有集合key中，使用LRU算法移除key

- volatile-random：在过期集合中移除随机的key，只对设置了过期时间的键

- allkeys-random：在所有集合key中，移除随机的key

- volatile-ttl：移除那些TTL值最小的key，即那些最近要过期的key

- noeviction：不进行移除。针对写操作，只是返回错误信息

	![1675314735136](C:\Users\huach\Desktop\账号\图标\1675314735136.png)



(4) maxmemory-samples

- 设置样本数量，LRU算法和最小TTL算法都并非是精确的算法，而是估算值，所以你可以设置样本的大小，redis默认会检查这么多个key并选择其中LRU的那个。

- 一般设置3到7的数字，数值越小样本越不准确，但性能消耗越小。

	![1675314789574](C:\Users\huach\Desktop\账号\图标\1675314789574.png)





#### 7) 小结

```shell
#支持远程访问
# bind 127.0.0.1 -::1
protected-mode no 

tcp-backlog 511 #连接队列大小
timeout 0 #永不超时
tcp-keepalive 300 #检查心跳时间

databases 16

#设置密码
```



### 5. 发布和订阅



#### 1) 什么是发布和订阅

Redis 发布订阅 (pub/sub) 是一种消息通信模式：发送者 (pub) 发送消息，订阅者 (sub) 接收消息。

Redis 客户端可以订阅任意数量的频道。

 

#### 2) Redis的发布和订阅

1、客户端可以订阅频道如下图

![1675251361991](E:\redis6笔记\images\1675251361991.png) 

2、当给这个频道发布消息后，消息就会发送给订阅的客户端

![1675251370864](E:\redis6笔记\images\1675251370864.png) 

 



#### 3) 发布订阅命令行实现

1、 打开一个客户端订阅channel1

SUBSCRIBE channel1

![1675315756956](C:\Users\huach\Desktop\账号\图标\1675315756956.png)



2、打开另一个客户端，给channel1发布消息hello

publish channel1 hello

![1675315819386](C:\Users\huach\Desktop\账号\图标\1675315819386.png)

返回的1是订阅者数量



3、打开第一个客户端可以看到发送的消息

![1675316003060](C:\Users\huach\Desktop\账号\图标\1675316003060.png)



注：发布的消息没有持久化，如果在订阅的客户端收不到hello，只能收到订阅后发布的消息





### 6. 新数据类型



#### 1) Bitmaps



##### (1) 简介

现代计算机用二进制（位） 作为信息的基础单位， 1个字节等于8位， 例如“abc”字符串是由3个字节组成， 但实际在计算机存储时将其用二进制表示， “abc”分别对应的ASCII码分别是 97、98、99， 对应的二进制分别是 01100001、01100010、01100011，如下图

![1675251569412](E:\redis6笔记\images\1675251569412.png)

合理地使用操作位能够有效地提高内存使用率和开发效率。



Redis提供了Bitmaps这个“数据类型”可以实现对位的操作：

- Bitmaps本身不是一种数据类型， 实际上它就是字符串（key-value），但是它可以对字符串的位进行操作。

- Bitmaps单独提供了一套命令， 所以在Redis中使用Bitmaps和使用字符串的方法不太相同。 可以把Bitmaps想象成一个以位为单位的数组，数组的每个单元只能存储0和1，数组的下标在Bitmaps中叫做偏移量。

![1675251628313](E:\redis6笔记\images\1675251628313.png)



##### (2) 命令

###### 1. setbit

**格式**：

`setbit <key> <offset> <value>` 设置 Bitmaps中某个偏移量的值（0或1）

![1675251712783](E:\redis6笔记\images\1675251712783.png)

`* offset`:偏移量从0开始

**实例:**

每个独立用户是否访问过网站存放在Bitmaps中， 将访问的用户记做1， 没有访问的用户记做0， 用偏移量作为用户的id。

设置键的第offset个位的值（从0算起），假设现在有20个用户，userid=1， 6， 11， 15， 19的用户对网站进行了访问， 那么当前Bitmaps初始

化结果如图

![1675251793941](E:\redis6笔记\images\1675251793941.png)

unique:users:20201106 代表2020-11-06这天的独立访问用户的Bitmaps 作为<key>

设置：

![1675251804886](E:\redis6笔记\images\1675251804886.png)

以上图中设置就完成了 效果图



**注**：

- 很多应用的用户id以一个指定数字（例如10000） 开头， 直接将用户id和Bitmaps的偏移量对应势必会造成一定的浪费， 通常的做法是每次做setbit操作时将用户id减去这个指定数字。

- 在第一次初始化Bitmaps时， 假如偏移量非常大， 那么整个初始化过程执行会比较慢， 可能会造成Redis的阻塞。



###### 2. getbit

**格式**:

`getbit <key> <offset>`获取Bitmaps中某个偏移量的值

![1675251969698](E:\redis6笔记\images\1675251969698.png)

获取键的第offset位的值（从0开始算）



**实例**:

获取id=8的用户是否在2020-11-06这天访问过， 获取的值为1说明访问过： 

![1675252014593](E:\redis6笔记\images\1675252014593.png)

注：因为100根本不存在，所以也是返回0



###### 3. bitcount

统计**字符串**被设置为1的bit数。一般情况下，给定的整个字符串都会被进行计数，通过指定额外的 start 或 end 参数，可以让计数只在特定的位上进行。start 和 end 参数的设置，都可以使用负数值：比如 -1 表示最后一个位，而 -2 表示倒数第二个位，start、end 是指bit组的字节的下标数，二者皆包含。



**格式：**

`bitcount<key>[start end]` 统计字符串从start字节到end字节比特值为1的数量

![1675252068937](E:\redis6笔记\images\1675252068937.png)

**实例**:

计算2022-11-06这天的独立访问用户数量（不标注 start end 表示统计整个字符串）

![1675252087425](E:\redis6笔记\images\1675252087425.png)



start和end代表起始和结束节数，下面操作计算用户id在第1个字节到第3个字节之间的独立访问用户数， 对应的用户id是11， 15， 19。

![1675252113037](E:\redis6笔记\images\1675252113037.png)



举例： K1 【01000001 01000000  00000000 00100001】，对应【0，1，2，3】

bitcount K1 1 2  ： 统计下标1、2字节组中bit=1的个数，即 01000000  00000000

--》`bitcount K1 1 2` => 1

 

bitcount K1 1 3  ： 统计下标1、3字节组中bit=1的个数，即 01000000 00000000 00100001

--》`bitcount K1 1 3` => 3

 

bitcount K1 0 -2  ： 统计下标0到下标倒数第2，字节组中bit=1的个数，即 01000001 01000000 00000000

--》`bitcount K1 0 -2` => 3



**注意**：redis的setbit设置或清除的是bit位置，而bitcount计算的是byte位置。





###### 4. bitop

格式

`bitop  and(or/not/xor) <destkey> [key…]`

![1675252162527](E:\redis6笔记\images\1675252162527.png)

bitop是一个复合操作， 它可以做多个 Bitmaps的 and（交集）、or（并集）、not（非）、xor（异或） 操作并将结果保存在destkey中。



**实例**

2020-11-04 日访问网站的userid=1,2,5,9。

`setbit unique:users:20201104 1 1`

`setbit unique:users:20201104 2 1`

`setbit unique:users:20201104 5 1`

`setbit unique:users:20201104 9 1`

  

2020-11-03 日访问网站的userid=0,1,4,9。

`setbit unique:users:20201103 0 1`

`setbit unique:users:20201103 1 1`

`setbit unique:users:20201103 4 1`

`setbit unique:users:20201103 9 1`

 

计算出两天都访问过网站的用户数量

`bitop and unique:users:and:20201104_03 unique:users:20201103 unique:users:20201104`

![1675252194832](E:\redis6笔记\images\1675252194832.png)



![1675252200219](E:\redis6笔记\images\1675252200219.png)



计算出任意一天都访问过网站的用户数量（例如月活跃就是类似这种），可以使用or求并集

`bitop or unique:users:and:20201104_03 unique:users:20201103 unique:users:20201104`

![1675252211518](E:\redis6笔记\images\1675252211518.png)



##### (3) Bitmaps与set对比

假设网站有1亿用户， 每天独立访问的用户有5千万， 如果每天用集合类型和Bitmaps分别存储活跃用户可以得到表

![1675252261064](E:\redis6笔记\images\1675252261064.png)



很明显， 这种情况下使用Bitmaps能节省很多的内存空间， 尤其是随着时间推移节省的内存还是非常可观的

![1675252285925](E:\redis6笔记\images\1675252285925.png)



但Bitmaps并不是万金油，假如该网站每天的独立访问用户很少，例如只有10万（大量的僵尸用户），那么两者的对比如下表所示，很显然，这时候使用Bitmaps就不太合适了，因为基本上大部分位都是0。

![1675252310521](E:\redis6笔记\images\1675252310521.png)





#### 2) HyperLogLog



##### (1) 简介

在工作当中，我们经常会遇到与统计相关的功能需求，比如统计网站PV（PageView页面访问量）,可以使用Redis的incr、incrby轻松实现。

但像UV（UniqueVisitor，独立访客）、独立IP数、搜索记录数等需要去重和计数的问题如何解决？这种求集合中不重复元素个数的问题称为基数问题。



解决基数问题有很多种方案：

- 数据存储在MySQL表中，使用distinct count计算不重复个数
- 使用Redis提供的hash、set、bitmaps等数据结构来处理

以上的方案结果精确，但随着数据不断增加，导致占用空间越来越大，对于非常大的数据集是不切实际的。



能否能够降低一定的精度来平衡存储空间？Redis推出了HyperLogLog

- Redis HyperLogLog 是用来做基数统计的算法，HyperLogLog 的优点是，在输入元素的数量或者体积非常非常大时，计算基数所需的空间总是固定的、并且是很小的。

- 在 Redis 里面，每个 HyperLogLog 键只需要花费 12 KB 内存，就可以计算接近 2^64 个不同元素的基数。这和计算基数时，元素越多耗费内存就越多的集合形成鲜明对比。

- 但是，因为 HyperLogLog 只会根据输入元素来计算基数，而不会储存输入元素本身，所以 HyperLogLog 不能像集合那样，返回输入的各个元素。

 

什么是基数?

比如数据集 {1, 3, 5, 7, 5, 7, 8}， 那么这个数据集的基数集为 {1, 3, 5 ,7, 8}, 基数(不重复元素)为5。 基数估计就是在误差可接受的范围内，快速计算基数。



##### (2) 命令



###### 1. pfadd 

**格式**

`pfadd <key>< element> [element ...]`   添加指定元素到 HyperLogLog 中

![1675252428893](E:\redis6笔记\images\1675252428893.png)

**实例**

![1675252444927](E:\redis6笔记\images\1675252444927.png)

将所有元素添加到指定HyperLogLog数据结构中。如果执行命令后HLL估计的近似基数发生变化，则返回1，否则返回0。



###### 2. pfcount

**格式**

`pfcount<key> [key ...]` 计算HLL的近似基数，可以计算多个HLL，比如用HLL存储每天的UV，计算一周的UV可以使用7天的UV合并计算即可

 ![1675252485392](E:\redis6笔记\images\1675252485392.png)

**实例**

![1675252498799](E:\redis6笔记\images\1675252498799.png)



###### 3. pfmerge

**格式**

`pfmerge<destkey><sourcekey> [sourcekey ...]`  将一个或多个HLL合并后的结果存储在另一个HLL中，比如每月活跃用户可以使用每天的活跃用户来合并计算可得

![1675252544880](E:\redis6笔记\images\1675252544880.png)

**实例**

![1675252558935](E:\redis6笔记\images\1675252558935.png)





#### 3) Geospatial

##### (1) 简介

Redis 3.2 中增加了对GEO类型的支持。GEO，Geographic，地理信息的缩写。该类型，就是元素的2维坐标，在地图上就是经纬度。redis基于该类型，提供了经纬度设置，查询，范围查询，距离查询，经纬度Hash等常见操作。

##### (2) 命令

###### 1. geoadd

**格式**

`geoadd <key> <longitude> <latitude> <member> [longitude latitude member...]`   添加地理位置（经度，纬度，名称）

![1675252640960](E:\redis6笔记\images\1675252640960.png)



实例

geoadd china:city 121.47 31.23 shanghai

geoadd china:city 106.50 29.53 chongqing 114.05 22.52 shenzhen 116.38 39.90 beijing

![1675252663900](E:\redis6笔记\images\1675252663900.png)



两极无法直接添加，一般会下载城市数据，直接通过 Java 程序一次性导入。

有效的经度从 -180 度到 180 度。有效的纬度从 -85.05112878 度到 85.05112878 度。

当坐标位置超出指定范围时，该命令将会返回一个错误。

已经添加的数据，是无法再次往里面添加的。



###### 2. geopos  

**格式**

`geopos <key><member> [member...]`  获得指定地区的坐标值

![1675252706176](E:\redis6笔记\images\1675252706176.png)

**实例**

![1675252718475](E:\redis6笔记\images\1675252718475.png)



###### 3. geodist

**格式**

`geodist<key><member1><member2>  [m|km|ft|mi ]`  获取两个位置之间的直线距离

 ![1675252747925](E:\redis6笔记\images\1675252747925.png)

**实例**

获取两个位置之间的直线距离

![1675252761753](E:\redis6笔记\images\1675252761753.png)

单位：

m 表示单位为米[默认值]。

km 表示单位为千米。

mi 表示单位为英里。

ft 表示单位为英尺。

如果用户没有显式地指定单位参数， 那么 GEODIST 默认使用米作为单位



###### 4. georadius

**格式**

`georadius<key>< longitude><latitude>radius  m|km|ft|mi`   以给定的经纬度为中心，找出某一半径内的元素

![1675252798740](E:\redis6笔记\images\1675252798740.png)

经度 纬度 距离 单位

**实例**

![1675252817964](E:\redis6笔记\images\1675252817964.png)





#### 4) 概括



**Bitmaps（实际就是字符串）**

```shell
setbit k1 1 1
setbit k1 10 1
getbit k1 1 #偏移量
bitcount k1
bitcount k1 1 2
bitop and newname k1 k2
```

**HyperLogLog**

基数问题

```shell
pfadd k1 value
pfcount k1
pfmerge k1 k2
```

**Geospatial**

地理位置，经纬度的操作

```shell
geoadd k1 106.50 29.53 chongqing
geopos k1 shanghai
geodist k1 shanghai chongqing km 两个位置的直线距离
georadius k1 110 30 1000 km 一个范围内的地点
```





### 7. Jedis



#### 1) Jedis所需要的jar包

```xml
<dependency>
<groupId>redis.clients</groupId>
<artifactId>jedis</artifactId>
<version>3.2.0</version>
</dependency>
```



#### 2) 连接Redis注意事项

(1) 禁用Linux的防火墙

​	查看是防火墙状态：`systemctl status firewalld`

​	![1675326335163](E:\redis6笔记\images\1675326335163.png)

​	禁用Linux的防火墙：`systemctl stop/disable firewalld.service`

(2) redis.conf 中注释掉 bind 127.0.0.1 ,然后 protected-mode no



#### 3) Jedis常用操作

##### (1) 创建动态的工程

##### (2) 创建测试程序

```java
package com.atguigu.jedis;
import redis.clients.jedis.Jedis;

public class Demo01 {
    
	public static void main(String[] args) {
        //创建 Jedis对象
		Jedis jedis = new Jedis("192.168.200.130",6379);// xshell中登录的Linux ip地址 和 redis监听的端口号
		//ping操作 测试链接
        String pong = jedis.ping();
		System.out.println("连接成功："+pong);
		jedis.close();
        
        //关闭链接
    	jedis.close();
	}
    
}
```



#### 4) 测试相关数据类型

##### (1) Jedis-API: Key

```java
@Test
public void demo01(){
    //创建 Jedis对象
    Jedis jedis = new Jedis("192.168.200.130",6379);
    
    //设置 k-v
    jedis.set("k1", "v1");
    jedis.set("k2", "v2");
    jedis.set("k3", "v3");
    
    //获取所有key
    Set<String> keys = jedis.keys("*");
    System.out.println(keys.size());
    for (String key : keys) {
        System.out.println(key);
    }
    
    System.out.println(jedis.exists("k1"));
    System.out.println(jedis.ttl("k1"));
    System.out.println(jedis.get("k1"));
    
    //关闭链接
    jedis.close();
}
```



##### (2) Jedis-API: String

```java
@Test
public void demo02(){ 
    //创建 Jedis对象
    Jedis jedis = new Jedis("192.168.200.130",6379);
    
    //同时设置多个 k-v
	jedis.mset("str1","v1","str2","v2","str3","v3");
    //同时获取v
	System.out.println(jedis.mget("str1","str2","str3"));
    
    //关闭链接
    jedis.close();
}
```

##### (3) Jedis-API: List

```java
@Test
public void demo03(){
    //创建 Jedis对象
    Jedis jedis = new Jedis("192.168.200.130",6379);

    //向左添加多个值
	List<String> list = jedis.lrange("mylist",0,-1);
	for (String element : list) {
		System.out.println(element);
	}
    
    //关闭链接
    jedis.close();
    
}
```



##### (4) Jedis-API: set

```java
@Test
public void demo04(){
    //创建 Jedis对象
    Jedis jedis = new Jedis("192.168.200.130",6379);

    //添加数据
    jedis.sadd("orders", "order01");
    jedis.sadd("orders", "order02");
    jedis.sadd("orders", "order03");
    jedis.sadd("orders", "order04");
    
    //获取数据
    Set<String> smembers = jedis.smembers("orders");
    for (String order : smembers) {
    	System.out.println(order);
    }
    //删除数据
    jedis.srem("orders", "order02");
    
    //关闭链接
    jedis.close();
    
}
```



##### (5) Jedis-API: hash

```java
@Test
public void demo05(){
    //创建 Jedis对象
    Jedis jedis = new Jedis("192.168.200.130",6379);
    
    //添加
    jedis.hset("hash1","userName","lisi");
    
    //获取值
    String value = jedis.hget("hash1","userName");
    System.out.println(value); 
    
    //添加（直接添加一个map）
    Map<String,String> map = new HashMap<String,String>();
    map.put("telphone","13810169999");
    map.put("address","atguigu");
    map.put("email","abc@163.com");
    jedis.hmset("hash2",map);
    
    //获取多个值
    List<String> result = jedis.hmget("hash2", "telphone","email");
    for (String element : result) {
    System.out.println(element);
    }
    
    //关闭链接
    jedis.close();
    
}
```



##### (6) Jedis-API: zset

```java
@Test
public void demo06(){
    //创建 Jedis对象
    Jedis jedis = new Jedis("192.168.200.130",6379);
    
    //添加
    jedis.zadd("zset01", 100d, "z3");
    jedis.zadd("zset01", 90d, "l4");
    jedis.zadd("zset01", 80d, "w5");
    jedis.zadd("zset01", 70d, "z6");

    //获取所有值
    Set<String> zrange = jedis.zrange("zset01", 0, -1);
    for (String e : zrange) {
    System.out.println(e);
    }
    
    //关闭链接
    jedis.close();
}
```





#### 5) Redis_Jedis 实例

完成一个手机验证码功能

要求：

1、输入手机号，点击发送后随机生成6位数字码，2分钟有效

- 6位随机数：可以使用java中 Random 实现
- 2分钟有效：就将这个生成数(验证码) 存入redis 设置2分钟过期时间 

2、输入验证码，点击验证，返回成功或失败

- 从redis中获取 验证码 与输入的进行比较 
- redis中存放的验证码每过期，并和输入的一样 才返回成功

3、每个手机号每天只能输入3次

- 使用 incr 每次发送后 +1
- 到达 3 之后 就不能发送

![1675323752845](E:\redis6笔记\images\1675323752845.png)





```java
package com.lhc;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @Author: lhc
 * @Date: 2023/2/2 17:05
 * @ClassName:
 */
public class PhoneCode {

    public static void main(String[] args) {

        //模拟 验证码发送 按钮 
        verifyCode("19914655902");

        //模拟 用户输入验证码 登录
        getRedisCod("19914655902","637424");

    }


    /**
     * 生成6位随机数
     */
    public static String getCode() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            stringBuilder.append(rand);
        }
        return stringBuilder + "";
    }


    /**
     * 2. 保存验证码、设置手机发送次数
     *
     * @param phone 手机号
     */
    public static void verifyCode(String phone) {

        //1) 创建 Jedis对象
        Jedis jedis = new Jedis("192.168.200.130", 6379);

        //2) 拼接key（确保唯一性）
        //拼接手机发送次数的key
        String countKey = "VerifyCode" + phone + ":count";
        //拼接验证码key
        String codeKey = "VerifyCode" + phone + ":code";

        //3) 设置每个手机每天只能发送3次
        String count = jedis.get(codeKey);
        if (count == null) {
            //查不到表示当天第一次发送，设置发送次数为1，
            // 并且设置过期时间为1天（第二天 就又查不到了）
            jedis.setex(countKey, 24 * 60 * 60, "1");
        } else if (Integer.parseInt(count) <= 2) {
            //当天没有超过3次，值自增1
            jedis.incr(codeKey);
        } else if (Integer.parseInt(count) > 2) {
            //当天超过3次
            System.out.println("今天的发送次数已超过了3次");
            //关闭链接
            jedis.close();
        }

        //4) 发送验证码到redis中
        jedis.setex(codeKey, 120, getCode());
        jedis.close();
    }

    /**
     * 3.验证码校验
     *
     * @param phone 手机号
     * @param code  用户输入的验证码
     */
    public static void getRedisCod(String phone, String code) {

        //从redis中获取验证码
        Jedis jedis = new Jedis("192.168.200.130", 6379);
        //将用户输入的code 拼接成验证码key
        String codeKey = "VerifyCode" + phone + ":code";
        //获取
        String redisCode = jedis.get(codeKey);

        //判断
        if (code.equals(redisCode)) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }

        jedis.close();
    }

}

```





### 8. Redis与Spring Boot整合

**整合步骤**

#### 1) 在pom.xml文件中引入redis相关依赖

```xml
<!-- redis -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

<!-- spring2.X集成redis所需common-pool2-->
<dependency>
	<groupId>org.apache.commons</groupId>
	<artifactId>commons-pool2</artifactId>
</dependency>

<!-- 其它用到的相关依赖 -->
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.12.1</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-annotations</artifactId>
    <version>2.11.4</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.11.4</version>
</dependency>
```

#### 2) application.properties配置redis配置

```properties
#Redis服务器地址
spring.redis.host=192.168.200.130
#Redis服务器连接端口
spring.redis.port=6379
#Redis数据库索引（默认为0）
spring.redis.database= 0
#连接超时时间（毫秒）
spring.redis.timeout=1800000
#连接池最大连接数（使用负值表示没有限制）
spring.redis.lettuce.pool.max-active=20
#最大阻塞等待时间(负数表示没限制)
spring.redis.lettuce.pool.max-wait=-1
#连接池中的最大空闲连接
spring.redis.lettuce.pool.max-idle=5
#连接池中的最小空闲连接
spring.redis.lettuce.pool.min-idle=0
```

#### 3) 添加redis配置类

```java
package com.lhc.redis_springboot.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @Author: lhc
 * @Date: 2023/2/2 19:20
 * @ClassName:
 */
@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setConnectionFactory(factory);
        //key序列化方式
        template.setKeySerializer(redisSerializer);
        //value序列化
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //value hashmap序列化
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 配置序列化（解决乱码的问题）,过期时间600秒
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(600))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();
        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
        return cacheManager;
    }
}

```

#### 4) 测试一下

RedisTestController中添加测试方法

```java
@RestController
@RequestMapping("/redisTest")
public class RedisTestController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public String testRedis() {
        //设置值到redis
        redisTemplate.opsForValue().set("name","lucy");
        //从redis获取值
        String name = (String)redisTemplate.opsForValue().get("name");
        return name;
    }
}
```

启动SpringBot项目，在浏览器访问 http://localhost:8080/redisTest

![1675338657184](E:\redis6笔记\images\1675338657184.png)



### 9. 事务_锁机制



#### 1) Redis的事务定义

![1675324145706](E:\redis6笔记\images\1675324145706.png)

Redis事务是一个单独的隔离操作：事务中的所有命令都会序列化、按顺序地执行。事务在执行的过程中，不会被其他客户端发送来的命令请求所打断。

Redis事务的主要作用就是串联多个命令防止别的命令插队。



#### 2) Multi、Exec、discard



- **Multi** 开启事务 组队

- **Exec** 提交事务 提交队列

- **Discard** 取消组队

	

从输入Multi命令开始，输入的命令都会依次进入命令队列中，但不会执行，直到输入Exec后，Redis会将之前的命令队列中的命令依次执行。

组队的过程中可以通过 Discard来放弃组队。  

![1675324182487](E:\redis6笔记\images\1675324182487.png)

**案例：**

![1675339114686](E:\redis6笔记\images\1675339114686.png)

组队成功，提交成功



![1675339189457](E:\redis6笔记\images\1675339189457.png)

组队过程中，使用discard 取消了组队



![1675339494229](E:\redis6笔记\images\1675339494229.png)

组队阶段报错，提交失败

**全部命令不能执行**



![1675339610513](E:\redis6笔记\images\1675339610513.png)

组队成功，提交有成功有失败情况

因为 第二个命令在组队过程中没报错，但是执行会报错("v1" 字符串 不能自增)

**种种情况不能影响其它 命令的执行**



#### 3) 事务的错误处理

组队阶段出错，整个队列取消

提交队列出错，出错的那个成员取消







#### 4) 为什么要做成事务

想想一个场景：有很多人有你的账户,同时去参加双十一抢购



#### 5) 事务冲突的问题



##### (1) 例子

一个请求想给金额减8000

一个请求想给金额减5000

一个请求想给金额减1000

![1675324425210](E:\redis6笔记\images\1675324425210.png)



##### (2) 悲观锁

![1675324437485](E:\redis6笔记\images\1675324437485.png)



**悲观锁(Pessimistic Lock)**, 顾名思义，就是很悲观，每次去拿数据的时候都认为别人会修改，**所以每次在拿数据的时候都会上锁**，这样别人想拿这个数据就会block直到它拿到锁。

**传统的关系型数据库里边就用到了很多这种锁机制**，比如**行锁**，**表锁**等，**读锁**，**写锁**等，**都是在做操作之前先上锁**。



##### (3) 乐观锁

![1675324466606](E:\redis6笔记\images\1675324466606.png)

**乐观锁(Optimistic Lock)**,顾名思义，就是很乐观，**每次去拿数据的时候都认为别人不会修改**，所以不会上锁，**但是在更新的时候会判断一下在此期间别人有没有去更新这个数据**（可以使用**版本号等机制** 更新数据也要将版本号更新，通过判断 拿数据时数据的版本号 与 更新数据时数据的版本号 进行对比，版本号一样才去更新 不一样退出更新操作）。

**乐观锁适用于多读的应用类型，这样可以提高吞吐量**。**Redis就是利用这种check-and-set机制实现事务的**。



**乐观锁案例**：抢火车票，当某一种火车票还剩最后一张的时候，所有人都可以进去抢票，但是只有最快的一个人可以支付成功(更新版本号)，其它人则失败



##### (4) WATCH key [key ...]

在执行multi之前，先执行 `watch key1 [key2 ...]`,可以监视一个(或多个) key ，如果在事务**执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断。**

![1675341086339](E:\redis6笔记\images\1675341086339.png)

![1675324549155](E:\redis6笔记\images\1675324549155.png)



##### (5) unwatch

取消 WATCH 命令对所有 key 的监视。

如果在执行 WATCH 命令之后，EXEC 命令或DISCARD 命令先被执行了的话，那么就不需要再执行UNWATCH 了。

http://doc.redisfans.com/transaction/exec.html



#### 6) Redis事务三特性

- 单独的隔离操作 

​	事务中的所有命令都会序列化、按顺序地执行。事务在执行的过程中，不会被其他客户端发送来的命令请求所打断。 

- 没有隔离级别的概念 

​	队列中的命令没有提交之前都不会实际被执行，因为事务提交前任何指令都不会被实际执行

- 不保证原子性 

​	事务中如果有一条命令执行失败，其后的命令仍然会被执行，没有回滚 





### 10. 事务相关的秒杀案例



#### 1) 解决计数器和人员记录的事务操作

![1675324882233](E:\redis6笔记\images\1675324882233.png)







#### 2) 秒杀并发模拟

```JAVA
package com.atguigu;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Transaction;

/**
 * 秒杀案例 基本实现
 */
public class SecKill_redis {

	public static void main(String[] args) {
		Jedis jedis =new Jedis("192.168.200.130",6379);
		System.out.println(jedis.ping());
		jedis.close();
	}

	//秒杀过程
	public static boolean doSecKill(String uid,String prodid) throws IOException {
        
		//1 uid和prodid非空判断
		if(uid == null || prodid == null) {
			return false;
		}

		//2 连接redis
		Jedis jedis = new Jedis("192.168.44.168",6379);

		//3 拼接key
		// 3.1 库存key
		String kcKey = "sk:"+prodid+":qt";
		// 3.2 秒杀成功用户key
		String userKey = "sk:"+prodid+":user";

		//4 获取库存，如果库存null，秒杀还没有开始
		String kc = jedis.get(kcKey);
		if(kc == null) {
			System.out.println("秒杀还没有开始，请等待");
			jedis.close();
			return false;
		}

		// 5 判断用户是否重复秒杀操作
		if(jedis.sismember(userKey, uid)) {
			System.out.println("已经秒杀成功了，不能重复秒杀");
			jedis.close();
			return false;
		}

		//6 判断如果商品数量，库存数量小于1，秒杀结束
		if(Integer.parseInt(kc)<=0) {
			System.out.println("秒杀已经结束了");
			jedis.close();
			return false;
		}

		//7 秒杀过程

		//7.1 库存-1
		jedis.decr(kcKey);
		//7.2 把秒杀成功用户添加清单里面
		jedis.sadd(userKey,uid);

		System.out.println("秒杀成功了..");
		jedis.close();
		return true;
	}
}
```



##### (1) 安装ab模拟测试

CentOS6 默认安装

CentOS7 需要手动安装

- 联网状态下直接执行：`yum install httpd-tools`

- 无网络：
	- 进入cd  /run/media/root/CentOS 7 x86_64/Packages（路径跟centos6不同）

	- 顺序安装

		apr-1.4.8-3.el7.x86_64.rpm

		apr-util-1.5.2-6.el7.x86_64.rpm

		httpd-tools-2.4.6-67.el7.centos.x86_64.rpm 

##### (2) ab参数 讲解

​	可使用 `ab --help` ，查看所有参数

![1675395619095](E:\redis6笔记\images\1675395619095.png)



##### (2) 测试及结果



**通过ab测试**

- 设置 库存数量 10

	![1675399728991](E:\redis6笔记\images\1675399728991.png)

- vim postfile 模拟表单提交参数, 以&符号结尾; 文件存放到当前目录( ~/postfile)

	![1675399885305](E:\redis6笔记\images\1675399885305.png)

	内容：prodid=0101&

	![1675399852656](E:\redis6笔记\images\1675399852656.png)

- 执行 `ab -n 1000 -c 100 -k -p ~/postfile -T application/x-www-form-urlencoded http://192.168.200.300:8081/Seckill/doseckill`

	（提交次数1000，并发次数100，post请求参数读取 ~/postfile，提交数据类型 application/x-www-form-urlencoded，请求路径 http://192.168.200.300:8081/Seckill/doseckill）



**结果出现超卖**

控制台打印：

![1675350905084](E:\redis6笔记\images\1675350905084.png)

查询库存数量： -1

 ![1675350920313](E:\redis6笔记\images\1675350920313.png)



#### 3) 超卖问题

![1675351045626](E:\redis6笔记\images\1675351045626.png)

#### 4) 乐观锁-解决超卖问题

 利用乐观锁淘汰用户，解决超卖问题

![1675351065987](E:\redis6笔记\images\1675351065987.png)

##### (1) 代码更新

```java
package com.atguigu;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Transaction;

/**
 * 使用乐观锁-实现秒杀
 */
public class SecKill_redis {

	public static void main(String[] args) {
		Jedis jedis =new Jedis("192.168.44.168",6379);
		System.out.println(jedis.ping());
		jedis.close();
	}

	//秒杀过程
	public static boolean doSecKill(String uid,String prodid) throws IOException {
		//1 uid和prodid非空判断
		if(uid == null || prodid == null) {
			return false;
		}

		//2 连接redis
		Jedis jedis = new Jedis("192.168.44.168",6379);


		//3 拼接key
		// 3.1 库存key
		String kcKey = "sk:"+prodid+":qt";
		// 3.2 秒杀成功用户key
		String userKey = "sk:"+prodid+":user";

		//监视库存
		jedis.watch(kcKey);

		//4 获取库存，如果库存null，秒杀还没有开始
		String kc = jedis.get(kcKey);
		if(kc == null) {
			System.out.println("秒杀还没有开始，请等待");
			jedis.close();
			return false;
		}

		// 5 判断用户是否重复秒杀操作
		if(jedis.sismember(userKey, uid)) {
			System.out.println("已经秒杀成功了，不能重复秒杀");
			jedis.close();
			return false;
		}

		//6 判断如果商品数量，库存数量小于1，秒杀结束
		if(Integer.parseInt(kc)<=0) {
			System.out.println("秒杀已经结束了");
			jedis.close();
			return false;
		}

		//7 秒杀过程
		//使用事务
		Transaction multi = jedis.multi();

		//组队操作
		multi.decr(kcKey);  //库存-1
		multi.sadd(userKey,uid);  //把秒杀成功用户添加清单里面

		//执行
		List<Object> results = multi.exec();
		
        //执行成功 代表秒杀成功
		if(results == null || results.size()==0) {
			System.out.println("秒杀失败了....");
			jedis.close();
			return false;
		}

		//7.1 库存-1
		//jedis.decr(kcKey);
		//7.2 把秒杀成功用户添加清单里面
		//jedis.sadd(userKey,uid);

		System.out.println("秒杀成功了..");
		jedis.close();
		return true;
	}
}
```



##### (2) 测试及结果

添加库存：设置10跟库存

执行：`ab -n 1000 -c 100 -k -p postfile -T 'application/x-www-form-urlencoded' http://192.168.140.1:8080/seckill/doseckill`

![1675351151085](E:\redis6笔记\images\1675351151085.png)

![1675351098507](E:\redis6笔记\images\1675351098507.png)



#### 5) 继续增加并发测试

设置库存：500

##### (1) 连接有限制

`ab -n 2000 -c 200 -k -p postfile -T 'application/x-www-form-urlencoded'   http://192.168.140.1:8080/seckill/doseckill`

![1675351544531](E:\redis6笔记\images\1675351544531.png)

增加 -r参数，-r   Don't exit on socket receive errors.

`ab -n 2000 -c 100 -r -p postfile -T 'application/x-www-form-urlencoded' http://192.168.140.1:8080/seckill/doseckill`



##### (2) 问题一：库存遗留

**ab -n 2000 -c 100 -p postfile -T 'application/x-www-form-urlencoded' http://192.168.137.1:8080/seckill/doseckill**

已经秒光，可是还有库存。**原因，就是乐观锁会导致很多请求都失败（监视和更新数过程中 有其他人购买成功了 就会导致 购买失败）。先点的没秒到，后点的可能秒到了**。

![1675351582158](E:\redis6笔记\images\1675351582158.png)



##### (3)  问题二：连接超时

![1675351610446](E:\redis6笔记\images\1675351610446.png)



#### 6) 解决超时问题



##### (1) 通过连接池解决

节省每次连接redis服务带来的消耗，把连接好的实例反复利用。

通过参数管理连接的行为

- 链接池参数
	- MaxTotal：控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；如果赋值为-1，则表示不限制；如果pool已经分配了MaxTotal个jedis实例，则此时pool的状态为exhausted。
	- maxIdle：控制一个pool最多有多少个状态为idle(空闲)的jedis实例；
	- MaxWaitMillis：表示当borrow一个jedis实例时，最大的等待毫秒数，如果超过等待时间，则直接抛JedisConnectionException；
	- testOnBorrow：获得一个jedis实例的时候是否检查连接可用性（ping()）；如果为true，则得到的jedis实例均是可用的；



redis连接池工具类

```java
package com.atguigu;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis连接池工具类
 */
public class JedisPoolUtil {
	private static volatile JedisPool jedisPool = null;

	private JedisPoolUtil() {
	}

	public static JedisPool getJedisPoolInstance() {
		if (null == jedisPool) {
			synchronized (JedisPoolUtil.class) {
				if (null == jedisPool) {
					JedisPoolConfig poolConfig = new JedisPoolConfig();
					poolConfig.setMaxTotal(200);
					poolConfig.setMaxIdle(32);
					poolConfig.setMaxWaitMillis(100*1000);
					poolConfig.setBlockWhenExhausted(true);
					poolConfig.setTestOnBorrow(true);  // ping  PONG
				 
					jedisPool = new JedisPool(poolConfig, "192.168.44.168", 6379, 60000 );
				}
			}
		}
		return jedisPool;
	}

	public static void release(JedisPool jedisPool, Jedis jedis) {
		if (null != jedis) {
			jedisPool.returnResource(jedis);
		}
	}

}

```



代码更新

```java
//2 连接redis
//Jedis jedis = new Jedis("192.168.44.168",6379);
//通过连接池得到jedis对象
JedisPool jedisPoolInstance = JedisPoolUtil.getJedisPoolInstance();
Jedis jedis = jedisPoolInstance.getResource();
```





#### 7) 解决库存遗留问题



##### (1) LUA脚本

Lua 是一个小巧的[脚本语言](http://baike.baidu.com/item/脚本语言)，Lua脚本可以很容易的被C/C++ 代码调用，也可以反过来调用C/C++的函数，Lua并没有提供强大的库，一个完整的Lua解释器不过200k，所以Lua不适合作为开发独立应用程序的语言，而是作为嵌入式脚本语言。

很多应用程序、游戏使用LUA作为自己的嵌入式脚本语言，以此来实现可配置性、可扩展性。

这其中包括魔兽争霸地图、魔兽世界、博德之门、愤怒的小鸟等众多游戏插件或外挂。

<https://www.w3cschool.cn/lua/>



##### (2) LUA脚本在 Redis中的优势

将复杂的或者多步的redis操作，写为一个脚本，一次提交给redis执行，减少反复连接redis的次数。提升性能。

LUA脚本是类似redis事务，有一定的原子性，不会被其他命令插队，可以完成一些redis事务性的操作。

但是注意redis的lua脚本功能，只有在Redis 2.6以上的版本才可以使用。

利用lua脚本淘汰用户，解决超卖问题。

redis 2.6版本以后，通过lua脚本解决**争抢问题**，实际上是**redis 利用其单线程的特性，用任务队列的方式解决多任务并发问题**。

![1675351754443](E:\redis6笔记\images\1675351754443.png)



##### (3) 使用lua脚本-实现秒杀

```java
package com.atguigu;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.joran.conditional.ElseAction;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Transaction;

/**
 * 使用lua脚本-实现秒杀
 */
public class SecKill_redisByScript {
	
	private static final  org.slf4j.Logger logger =LoggerFactory.getLogger(SecKill_redisByScript.class) ;


    //编写的脚本1
	static String secKillScript ="local userid=KEYS[1];\r\n" + 
			"local prodid=KEYS[2];\r\n" + 
			"local qtkey='sk:'..prodid..\":qt\";\r\n" + 
			"local usersKey='sk:'..prodid..\":usr\";\r\n" + 
			"local userExists=redis.call(\"sismember\",usersKey,userid);\r\n" + 
			"if tonumber(userExists)==1 then \r\n" + 
			"   return 2;\r\n" + 
			"end\r\n" + 
			"local num= redis.call(\"get\" ,qtkey);\r\n" + 
			"if tonumber(num)<=0 then \r\n" + 
			"   return 0;\r\n" + 
			"else \r\n" + 
			"   redis.call(\"decr\",qtkey);\r\n" + 
			"   redis.call(\"sadd\",usersKey,userid);\r\n" + 
			"end\r\n" + 
			"return 1" ;
    
	//编写的脚本2	 
	static String secKillScript2 = 
			"local userExists=redis.call(\"sismember\",\"{sk}:0101:usr\",userid);\r\n" +
			" return 1";

    //秒杀方法
	public static boolean doSecKill(String uid,String prodid) throws IOException {

        //从连接池中获取 jedis
		JedisPool jedispool =  JedisPoolUtil.getJedisPoolInstance();
		Jedis jedis=jedispool.getResource();

		//加载脚本
		String sha1=  jedis.scriptLoad(secKillScript);
        
        //抢购
		Object result= jedis.evalsha(sha1, 2, uid,prodid);

		String reString=String.valueOf(result);
		if ("0".equals( reString )  ) {
			System.err.println("已抢空！！");
		}else if("1".equals( reString )  )  {
			System.out.println("抢购成功！！！！");
		}else if("2".equals( reString )  )  {
			System.err.println("该用户已抢过！！");
		}else{
			System.err.println("抢购异常！！");
		}
        
		jedis.close();
		return true;
	}
}

```

代码中编写的lua脚本：

```lua
local userid=KEYS[1]; 										//定义变量			
local prodid=KEYS[2];										//定义变量
local qtkey="sk:"..prodid..":qt";  							//拼接 库存key
local usersKey="sk:"..prodid..":usr"; 						//拼接 用户key
local userExists=redis.call("sismember",usersKey,userid); 	//调用 redis中 sismember命令，判断用户是否在目前的清单中
if tonumber(userExists)==1 then 							
  return 2;													//用户存在 返回2
end
local num= redis.call("get" ,qtkey);						//调用 redis中 get命令 获取库存数
if tonumber(num)<=0 then 									
  return 0; 												//库存<=0 返回0
else 														//否则
  redis.call("decr",qtkey);									//调用 redis中 decr命令，让库存 -1 	
  redis.call("sadd",usersKey,userid);						//调用 redis中 sadd命令，将用户存入redis
end
return 1;													//返回1
```



#### 8) 总结案例中的几个版本



(1) 第一版：简单版

​	老师点10次，正常秒杀

​	同学一起点试一试，秒杀也是正常的。这是因为还达不到并发的效果。

​	使用工具ab模拟并发测试，会出现超卖情况。查看库存会出现负数。

(3) 第二版：加事务-乐观锁(解决超卖),但出现遗留库存和连接超时

(4) 第三版：连接池解决超时问题

(5) 第四版：解决库存依赖问题，LUA脚本

![1675351789167](E:\redis6笔记\images\1675351789167.png)



### 11. Redis持久化之 RDB



#### 1) 总体介绍

官网介绍：http://www.redis.io



Redis 提供了2个不同形式的持久化方式。

- RDB（Redis DataBase）
- AOF（Append Of File）



#### 2) RDB（Redis DataBase）



##### (1) 官网介绍

http://www.redis.io

##### (2) 是什么RDB

在指定的**时间间隔**内将内存中的**数据集快照**写入磁盘， 也就是行话讲的Snapshot快照，它恢复时是将快照文件直接读到内存里



##### (3) 备份是如何执行的

Redis会单独创建（fork）一个子进程来进行持久化，会先将数据写入到 一个临时文件中，待持久化过程都结束了，再用这个临时文件替换上次持久化好的文件。 整个过程中，主进程是不进行任何IO操作的，这就确保了极高的性能 如果需要进行大规模数据的恢复，且对于数据恢复的完整性不是非常敏感，那RDB方式要比AOF方式更加的高效。**RDB的缺点是**最后一次持久化后的数据可能丢失。



##### (4) Fork

- Fork的作用是复制一个与当前进程一样的进程。新进程的所有数据（变量、环境变量、程序计数器等） 数值都和原进程一致，但是是一个全新的进程，并作为原进程的子进程
- 在Linux程序中，fork()会产生一个和父进程完全相同的子进程，但子进程在此后多会exec系统调用，出于效率考虑，Linux中引入了“**写时复制技术**”
- **一般情况父进程和子进程会共用同一段物理内存**，只有进程空间的各段的内容要发生变化时，才会将父进程的内容复制一份给子进程。



##### (5) RDB持久化流程

![1675352116643](E:\redis6笔记\images\1675352116643.png)



##### (6) dump.rdb文件

在redis.conf中配置文件名称，默认为dump.rdb

![1675352142206](E:\redis6笔记\images\1675352142206.png)



##### (7) 配置位置

rdb文件的保存路径，也可以修改。默认为Redis启动时命令行所在的目录下

dir "/myredis/"

![1675352169781](E:\redis6笔记\images\1675352169781.png)



##### (8) 如何触发RDB快照；保持策略



**配置文件中默认的快照配置：**



**1) flushall命令**

执行flushall命令，也会产生dump.rdb文件，但里面是空的，无意义



**2) Save** 

格式：`Save 秒钟 写操作次数`

RDB是整个内存的压缩过的Snapshot，RDB的数据结构，可以配置复合的快照触发条件，

![1675352207398](E:\redis6笔记\images\1675352207398.png)

**默认是1分钟内改了1万次，或5分钟内改了10次，或15分钟内改了1次。**

建议禁用，不设置save指令，或者给save传入空字符串



**命令 save VS bgsave**

save ：save时只管保存，其它不管，全部阻塞。手动保存。不建议。

bgsave：Redis会在后台异步进行快照操作， 快照同时还可以响应客户端请求。

可以通过lastsave 命令获取最后一次成功执行快照的时间



**3) stop-writes-on-bgsave-error**

![1675352344106](E:\redis6笔记\images\1675352344106.png)

当Redis无法写入磁盘的话，直接关掉Redis的写操作。推荐yes.



**4) rdbcompression** **压缩文件**

![1675352366042](E:\redis6笔记\images\1675352366042.png)

储到磁盘中的快照，可以设置是否进行压缩存储。如果是的话，redis会采用LZF算法进行压缩。

如果你不想消耗CPU来进行压缩的话，可以设置为关闭此功能。推荐yes



**5) rdbchecksum** **检查完整性**

![1675352390033](C:\Users\huach\AppData\Roaming\Typora\typora-user-images\1675352390033.png)

在存储快照后，还可以让redis使用CRC64算法来进行数据校验，

但是这样做会增加大约10%的性能消耗，如果希望获取到最大的性能提升，可以关闭此功能

推荐yes.



##### (9) rdb的备份

先通过 `config get dir`  查询rdb文件的目录 

将 dump.rdb 文件拷贝出来（假如拷贝为 d.rdb）

rdb的恢复

- 关闭 Redis (kill -9 进程号)
- 先把备份的文件拷贝到工作目录下 cp d.rdb dump.rdb
- 启动Redis, 备份数据会直接加载



##### (10) 优劣势



**优势**

- 适合大规模的数据恢复
- 对数据完整性和一致性要求不高更适合使用
- 节省磁盘空间
- 恢复速度快

![1675352463003](E:\redis6笔记\images\1675352463003.png)



**劣势**

- Fork的时候，内存中的数据被克隆了一份，大致2倍的膨胀性需要考虑
- 虽然Redis在fork时使用了**写时拷贝技术**,但是如果数据庞大时还是比较消耗性能。
- 在备份周期在一定间隔时间做一次备份，所以**如果Redis意外down掉的话，就会丢失最后一次快照后的所有修改**。



##### **(11) 如何停止**

动态停止RDB：`redis-cli config set save ""`

永久停止RDB：配置文件中 #save后给空值，表示禁用保存策略



##### **(12) 小总结**

![1675352508515](E:\redis6笔记\images\1675352508515.png)







### 12. Redis持久化之 AOF



#### 1) AOF（Append Only File）



##### (1) 是什么AOF

以**日志**的形式来记录每个写操作（增量保存），将Redis执行过的所有写指令记录下来(**读操作不记录**)， **只许追加文件但不可以改写文件**，redis启动之初会读取该文件重新构建数据，换言之，redis 重启的话就根据日志文件的内容将写指令从前到后执行一次以完成数据的恢复工作



##### (2) AOF持久化流程



- 客户端的请求写命令会被append追加到AOF缓冲区内；
- AOF缓冲区根据AOF持久化策略[always,everysec,no]将操作sync同步到磁盘的AOF文件中；
- AOF文件大小超过重写策略或手动重写时，会对AOF文件rewrite重写，压缩AOF文件容量；
- Redis服务重启时，会重新load加载AOF文件中的写操作达到数据恢复的目的；

![1675352715204](E:\redis6笔记\images\1675352715204.png)



##### (3) AOF默认不开启

可以在redis.conf中配置文件名称，默认为 appendonly.aof

![1675420908038](E:\redis6笔记\images\1675420908038.png)

开启之后会在 同RDB的同级目录下生成一个 appendonlydir文件夹 里面就是 AOF文件

![1675421373034](E:\redis6笔记\images\1675421373034.png)





##### (4) AOF和 RDB 同时开启，redis 听谁的？

AOF和RDB同时开启，系统默认取AOF的数据（数据不会存在丢失）



##### (5) AOF恢复

- AOF的备份机制和性能虽然和RDB不同, 但是**备份和恢复的操作同RDB一样**，都是拷贝备份文件，需要恢复时再拷贝到Redis工作目录下，启动系统即加载。

- 正常恢复

	- 将之前备份的aof文件复制一份保存到对应目录 (查看目录：config get dir)
	- 恢复：重启redis然后重新加载 

- 异常恢复：

	   当AOF文件损坏了，（测试AOF损坏 我们可以直接去修改 appendonly.aof文件 比如在文件的末行 添加一个Hello）

	   我们关闭redis，再去重新启动，redis会去读取 AOF文件，由于文件损坏了，导致redis启动失败

	​	![1675421922794](E:\redis6笔记\images\1675421922794.png)

	​	就要进行异常恢复：

	- 如遇到**AOF文件损坏**，通过 `/usr/local/bin/redis-check-aof--fix 需要修复的文件名` 进行恢复

		![1675422334845](E:\redis6笔记\images\1675422334845.png)

	- 备份被写坏的AOF文件

	- 恢复：重启redis，然后重新加载



##### (6) AOF同步频率设置

**appendfsync always** 

始终同步，每次Redis的写入都会立刻记入日志；性能较差但数据完整性比较好

**appendfsync everysec**

每秒同步，每秒记入日志一次，如果宕机，本秒的数据可能丢失。

**appendfsync no**

redis不主动进行同步，把同步时机交给操作系统。



##### (7) Rewrite压缩

1、是什么Rewrite压缩：

- AOF采用文件追加方式，文件会越来越大。 为避免出现此种情况，新增了重写机制, 当AOF文件的大小超过所设定的阈值时，Redis就会启动AOF文件的内容压缩， **只保留可以恢复数据的最小指令集**. 可以使用命令 bgrewriteaof

​	例如 执行了 **set a1 b1** 、**set a2 b2**、**set a2 b3**  启动内容压缩就会变成 **mset a1 b1 a2 b3**



2、重写原理

**如何实现重写**

- AOF文件持续增长而过大时，会fork出一条新进程来将文件重写(也是先写临时文件最后再rename)，redis4.0版本后的重写，是指上就是把 rdb 的快照，以二级制的形式附在新的aof头部，作为已有的历史数据，替换掉原来的流水账操作。  

	**no-appendfsync-on-rewrite：**

- 如果 no-appendfsync-on-rewrite=yes ,不写入aof文件只写入缓存，用户请求不会阻塞，但是在这段时间如果宕机会丢失这段时间的缓存数据。（降低数据安全性，提高性能）

- 如果 no-appendfsync-on-rewrite=no,  还是会把数据往磁盘里刷，但是遇到重写操作，可能会发生阻塞。（数据安全，但是性能降低）

**触发机制，何时重写：**

- Redis会记录上次重写时的AOF大小，默认配置是当AOF文件大小是上次rewrite后大小的一倍且文件大于64M时触发

- 重写虽然可以节约大量磁盘空间，减少恢复时间。但是每次重写还是有一定的负担的，因此设定Redis要满足一定条件才会进行重写。 

- auto-aof-rewrite-min-size：设置重写的基准值，最小文件64MB。达到这个值开始重写。

	auto-aof-rewrite-percentage：设置重写的基准值，文件达到100%时开始重写（重写之后 文件达到重写文件的 2倍，再进行重写）

	例如：文件达到70MB开始重写，降到50MB，下次什么时候开始重写？100MB

- 系统载入时或者上次重写完毕时，Redis会记录此时AOF大小，设为base_size,

	如果Redis的AOF当前大小>= base_size +base_size*100% (默认)且当前大小>=64mb(默认)的情况下，Redis会对AOF进行重写。 



3、重写流程

(1) bgrewriteaof触发重写，判断是否当前有bgsave或bgrewriteaof在运行，如果有，则等待该命令结束后再继续执行。

(2) 主进程fork出子进程执行重写操作，保证主进程不会阻塞。

(3) 子进程遍历redis内存中数据到临时文件，客户端的写请求同时写入aof_buf缓冲区和aof_rewrite_buf重写缓冲区保证原AOF文件完整以及新AOF文件生成期间的新的数据修改动作不会丢失。

(4) ①子进程写完新的AOF文件后，向主进程发信号，父进程更新统计信息。

​	② 主进程把aof_rewrite_buf中的数据写入到新的AOF文件。

(5) 使用新的AOF文件覆盖旧的AOF文件，完成AOF重写。

![1675352950215](E:\redis6笔记\images\1675352950215.png)





##### (8) 优势

![1675352982744](E:\redis6笔记\images\1675352982744.png)

- 备份机制更稳健，丢失数据概率更低。
- 可读的日志文本，通过操作AOF稳健，可以处理误操作。



##### (9) 劣势

- 比起RDB占用更多的磁盘空间。
- 恢复备份速度要慢。
- 每次读写都同步的话，有一定的性能压力。
- 存在个别Bug，造成不能恢复。



##### (10) 小总结

![1675353064363](E:\redis6笔记\images\1675353064363.png)



#### 2) 总结 (Which one)



##### (1) 用哪个好

官方推荐两个都启用。

如果对数据不敏感，可以选单独用RDB。

不建议单独用 AOF，因为可能会出现Bug。

如果只是做纯内存缓存，可以都不用。



(2) 官网建议

- RDB持久化方式能够在指定的时间间隔能对你的数据进行快照存储

- AOF持久化方式记录每次对服务器写的操作,当服务器重启的时候会重新执行这些命令来恢复原始的数据,AOF命令以redis协议追加保存每次写的操作到文件末尾. 

- Redis还能对AOF文件进行后台重写,使得AOF文件的体积不至于过大

- 只做缓存：如果你只希望你的数据在服务器运行的时候存在,你也可以不使用任何持久化方式.

- 同时开启两种持久化方式

	在这种情况下,当redis重启的时候会优先载入AOF文件来恢复原始的数据, 因为在通常情况下AOF文件保存的数据集要比RDB文件保存的数据集要完整.

- RDB的数据不实时，同时使用两者时服务器重启也只会找AOF文件。那要不要只使用AOF呢？ 

	建议不要，因为RDB更适合用于备份数据库(AOF在不断变化不好备份)， 快速重启，而且不会有AOF可能潜在的bug，留着作为一个万一的手段。

- 性能建议

	因为RDB文件只用作后备用途，建议只在Slave(以读为主)上持久化RDB文件，而且只要15分钟备份一次就够了，只保留 save 900 1 这条规则。 如果使用AOF，好处是在最恶劣情况下也只会丢失不超过两秒数据，启动脚本较简单只load自己的AOF文件就可以了。

	代价：一是带来了持续的IO，二是AOF rewrite的最后将rewrite过程中产生的新数据写到新文件造成的阻塞几乎是不可避免的。

	只要硬盘许可，应该尽量减少AOF rewrite的频率，AOF重写的基础大小默认值64M太小了，可以设到5G以上。默认超过原大小100%大小时重写可以改到适当的数值。







### 13. 主从复制



#### 1) 是什么

主机数据更新后根据配置和策略， 自动同步到备机的master/slaver机制，**Master以写为主，Slave以读为主**



#### 2) 能干嘛

- 读写分离，性能扩展

	主服务器 做写操作，从服务器 做读操作

- 容灾快速恢复

	复制出多个 从服务器，如果一个从服务器挂掉了，就可以去其它的 从服务器读取数据

	（主机挂掉了我们应该如何应对？，那就要使用集群了，下一章讲解）

![1675353337001](E:\redis6笔记\images\1675353337001.png)



#### 3) 怎么玩：主从复制

拷贝多个redis.conf文件include(写绝对路径)

开启daemonize yes

Pid文件名字pidfile

指定端口port

Log文件名字

dump.rdb名字dbfilename

Appendonly 关掉或者换名字



##### (1) 创建 /myredis 文件夹

我们在这里面玩

![1675425264007](E:\redis6笔记\images\1675425264007.png)

##### (2) 复制 redis.conf 配置文件到文件夹中

![1675425377478](E:\redis6笔记\images\1675425377478.png)



##### (3) 配置一主两从

创建三个配置文件

**1、新建redis6379.conf，填写以下内容**

```
include /myredis/redis.conf 	【include 文件1：引入文件中的内容到改文件中】
pidfile /var/run/redis_6379.pid 【指定配置pid进程文件(存储当前redis进程号等信息) 位置】
port 6379					    【设置端口号】
dbfilename dump6379.rdb		    【指定rdb文件名称】
```

![1675353395129](E:\redis6笔记\images\1675353395129.png)



**2、新建redis6380.conf，填写以下内容**

![1675353417926](E:\redis6笔记\images\1675353417926.png)



**3、新建redis6381.conf，填写以下内容**

![1675353437718](E:\redis6笔记\images\1675353437718.png)



slave-priority 10

设置从机的优先级，值越小，优先级越高，用于选举主机时使用。默认100



##### (4) 启动三台redis服务器

![1675353467025](E:\redis6笔记\images\1675353467025.png)



##### (5) 查看三台服务器是否启动

![1675353487729](E:\redis6笔记\images\1675353487729.png)



##### (6) 查看三台主机运行情况

打印主从复制的相关信息: `info replication`

![1675426760253](E:\redis6笔记\images\1675426760253.png)

![1675426822251](E:\redis6笔记\images\1675426822251.png)

![1675426917773](E:\redis6笔记\images\1675426917773.png)

目前3个 都是主机



##### (7) 配从(库)不配主(库)

在其中两个服务器上执行： `slaveof <ip> <port>`  成为某个实例的从服务器



1、在 6380 和 6381 上执行: `slaveof 127.0.0.1 6379`

![1675427120233](E:\redis6笔记\images\1675427120233.png)

![1675427220256](E:\redis6笔记\images\1675427220256.png)



再次执行：`info replication` 对比信息

![1675427724328](E:\redis6笔记\images\1675427724328.png)



2、在主机上写，在从机上可以读取数据

在主机上写操作，与读操作

![1675427840778](E:\redis6笔记\images\1675427840778.png)

在从机上读数据

![1675427931376](E:\redis6笔记\images\1675427931376.png)

在从机上写数据会报错

![1675427960421](E:\redis6笔记\images\1675427960421.png)



3、主机挂掉，重启就行，一切如初



4、从机重启需重设：`slaveof 127.0.0.1 6379`

可以将配置增加到文件中。永久生效。

![1675353592155](E:\redis6笔记\images\1675353592155.png)



#### 4) 常用3招



##### (1) 一主二仆

- 切入点问题（如果从服务器挂掉后重启 是从头开始复制还是从切入点开始复制）?  ：

	从服务器挂掉重启 并重新设置为从服务器 他会从头开始复制主服务器的东西

	比如 之前主服务器设置了k1,k2,k3  从服务器挂掉重启 还能查询到 主服务器之前设置的所有东西



- 从机是否可以写？set可否？ 

	从机不行进行写操作，会报错

- 主机shutdown后情况如何？从机是上位还是原地待命？

	一切如初

- 主机又回来了后，主机新增记录，从机还能否顺利复制？ 

	可以

- 其中一台从机down后情况如何？依照原有它能跟上大部队吗？

	可以

![1675353669005](E:\redis6笔记\images\1675353669005.png)



##### (2) 薪火相传

上一个Slave可以是下一个slave的Master，Slave同样可以接收其他 slaves的连接和同步请求，那么该slave作为了链条中下一个的master, **可以有效减轻master的写压力**,去中心化降低风险。

用 `slaveof <ip> <port>`

**特点**

- 中途变更转向:会清除之前的数据，重新建立拷贝最新的

- 风险是一旦某个slave宕机，后面的slave都没法备份

- 主机挂了，从机还是从机，无法写数据了

![1675353698073](E:\redis6笔记\images\1675353698073.png)

![1675353703846](E:\redis6笔记\images\1675353703846.png)





##### (3) 反客为主

当一个master宕机后，后面的slave可以立刻升为master，其后面的slave不用做任何修改。

用 `slaveof no one`  将从机变为主机。

![1675353732249](E:\redis6笔记\images\1675353732249.png)





#### 5) 主从复制原理

- Slave(从)启动成功 连接到 master(主)后 会发送一个 sync同步命令
- Master接到命令 启动后台的存盘进程，同时收集所有接收到的用于修改数据集命令， 在后台进程执行完毕之后，master将传送整个数据文件到slave,以完成一次完全同步
- 全量复制：而slave服务在接收到数据库文件数据后，将其存盘并加载到内存中。
- 增量复制：每次Master进行写操作之后，会继续将新的所有收集到的修改命令依次传给slave,完成同步
- 但是只要是重新连接master,一次完全同步（全量复制)将被自动执行

![1675353789409](E:\redis6笔记\images\1675353789409.png)



#### 6) 哨兵模式(sentinel)



##### (1) 是什么

**反客为主的自动版**，能够后台监控主机是否故障，如果故障了根据投票数自动将从库转换为主库

![1675353836537](E:\redis6笔记\images\1675353836537.png)



##### (2) 怎么玩(使用步骤)



**1、调整为一主二仆模式，6379带着6380、6381**

![1675353870133](E:\redis6笔记\images\1675353870133.png)



**2、自定义的/myredis目录下新建 sentinel.conf文件，名字绝不能错**



**3、配置哨兵,编辑上面新建的配置文件，填写内容：**

`sentinel monitor mymaster 127.0.0.1 6379 1`

其中

​	mymaster 为监控对象起的服务器名称(给主机取的外号)， 

​	127.0.0.1 6379 为主机的ip和端口号，

​	1 为至少有多少个哨兵同意迁移的数量（当主机挂掉了，需要至少1个哨兵 同意才能 选举别的为主机）。 



**4、启动哨兵**

切换到 /usr/local/bin 目录

redis做压测可以用自带的redis-benchmark工具

执行`redis-sentinel /myredis/sentinel.conf `

![1675353950912](E:\redis6笔记\images\1675353950912.png)





**5、当主机挂掉，自动从机选举中产生新的主机**

(大概10秒左右可以看到哨兵窗口日志，切换了新的主机)

哪个从机会被选举为主机呢？根据优先级别：**slave-priority** 

原主机重启后会变为从机。

![1675490009826](E:\redis6笔记\images\1675490009826.png)



**复制延时**

由于所有的写操作都是先在Master上操作，然后同步更新到Slave上，所以从Master同步到Slave机器有一定的延迟，当系统很繁忙的时候，延迟问题会更加严重，Slave机器数量的增加也会使这个问题更加严重。



##### (3) 故障恢复

![1675354034765](E:\redis6笔记\images\1675354034765.png)

优先级：在redis.conf中默认：**slave-priority 100**，值越小优先级越高

偏移量：是指获得原主机数据最全的

runid：每个redis实例启动后都会随机生成一个40位的runid



##### (4) 在java中如何配置

主从复制 哨兵模式 在java中如何配置

```java
private static JedisSentinelPool jedisSentinelPool=null;

public static  Jedis getJedisFromSentinel(){
if(jedisSentinelPool==null){
            Set<String> sentinelSet=new HashSet<>();
            sentinelSet.add("192.168.200.130:26379");

            JedisPoolConfig jedisPoolConfig =new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(10); //最大可用连接数
            jedisPoolConfig.setMaxIdle(5); //最大闲置连接数
            jedisPoolConfig.setMinIdle(5); //最小闲置连接数
            jedisPoolConfig.setBlockWhenExhausted(true); //连接耗尽是否等待
            jedisPoolConfig.setMaxWaitMillis(2000); //等待时间
            jedisPoolConfig.setTestOnBorrow(true); //取连接的时候进行一下测试 ping pong

            jedisSentinelPool=new JedisSentinelPool("mymaster",sentinelSet,jedisPoolConfig);
            return jedisSentinelPool.getResource();
        }else{
            return jedisSentinelPool.getResource();
        }
}
```







### 14. Redis集群



#### 1) 问题

容量不够，redis如何进行扩容？

并发写操作， redis如何分摊？

另外，主从模式，薪火相传模式，主机宕机，导致ip地址发生变化，应用程序中配置需要修改对应的主机地址、端口等信息。

之前通过代理主机来解决，但是redis3.0中提供了解决方案。就是**无中心化集群配置**。



#### 2) 什么是集群

Redis 集群实现了对Redis的水平扩容，即启动N个redis节点，将整个数据库分布存储在这N个节点中，每个节点存储总数据的1/N。

Redis 集群通过分区（partition）来提供一定程度的可用性（availability）： 即使集群中有一部分节点失效或者无法进行通讯， 集群也可以继续处理命令请求。



#### 3) 删除持久化数据

将 /myredis 目录中 的rdb,aof文件都删除掉（防止 被上一章留下的文件 干扰）

我们还在这个目录中 做实验

#### 4) 制作6个redis

复制 redis.conf配置文件到 /myredis目录中 (上一章操作过)

配置6个配置文件：

​	redis6379.conf

​	redis6380.conf

​	redis6381.conf

​	redis6389.conf

​	redis6390.conf

​	redis6391.conf

##### (1) 修改 redis.conf 文件配置

​    在redis.conf配置文件中：

​	  daemonize yes

​	  appendonly 关掉或者换名字

##### (2) redis6379.conf 配置文件修改修改

cluster-enabled yes    				【打开集群模式】

cluster-config-file nodes-6379.conf    【设定节点配置文件名】

cluster-node-timeout 15000   		  【设定节点失联时间，超过该时间（毫秒），集群自动进行主从切换】

 ```java
include /myredis/redis.conf 						 	 【引入 redis.conf文件中的内容到该文件中】		
port 6379												 【指定端口】	
pidfile "/var/run/redis_6379.pid"						 【指定Pid文件名字】
dbfilename "dump6379.rdb"								 【指定Dump.rdb名字】
dir "/myredis/redis_cluster"
logfile "/myredis/redis_cluster/redis_err_6379.log" 	 【指定Log文件名字】

cluster-enabled yes										 【打开集群模式】
cluster-config-file nodes-6379.conf						 【设定节点配置文件名】
cluster-node-timeout 15000								 【设定节点失联时间，超过该时间（毫秒），集群自动进行主从切换】
 ```



##### (3) 修改好 redis6379.conf 文件，拷贝多个redis.conf文件 

分别改名、并修改配置

![1675354388416](E:\redis6笔记\images\1675354388416.png)



##### (4) 使用查找替换修改另外5个文件

例如在 redis6380.conf中执行 `:%s/6379/6380`

其余配置文件 依次操作

![1675493638192](E:\redis6笔记\images\1675493638192.png)



##### (5) 启动6个redis服务

![1675354447783](E:\redis6笔记\images\1675354447783.png)



#### 5) 将六个节点合成一个集群

组合之前，请确保所有redis实例启动后，nodes-xxxx.conf文件都生成正常。

![1675354502563](E:\redis6笔记\images\1675354502563.png)



**合体**：

cd /opt/redis-7.0.8/src

在这个目录中执行：

```
redis-cli --cluster create --cluster-replicas 1 192.168.200.130:6379 192.168.200.130:6380 192.168.200.130:6381 
192.168.200.130:6389 192.168.200.130:6390 192.168.200.130:6391
```

（此处不要用127.0.0.1， 请用真实IP地址）

命令中的 `--cluster-replicas 1` 表示 采用最简单的方式配置集群，一台主机带一台从机，正好三组。

![1675354564321](E:\redis6笔记\images\1675354564321.png)

![1675354572763](E:\redis6笔记\images\1675354572763.png)



#### 6) 登录链接



##### (1) 普通方式登录

执行命令: `redis-cli -p 6379`

这样可能直接进入读主机，存储数据时，会出现MOVED重定向操作。所以，应该以集群方式登录。

![1675354627873](E:\redis6笔记\images\1675354627873.png)

![1675354633208](E:\redis6笔记\images\1675354633208.png)

##### (2) 以集群方式登录

执行命令: `redis-cli -c -p 6379`

-c 表示采用集群策略连接，设置数据会自动切换到相应的写主机

![1675354661209](E:\redis6笔记\images\1675354661209.png)



#### 7) 查看集群信息

 执行命令 `cluster nodes`  查看集群信息

![1675495130149](E:\redis6笔记\images\1675495130149.png)



#### 8) 如何分配这六个节点?

一个集群至少要有三个主节点。

选项 **--cluster-replicas 1** 表示我们希望为集群中的每个主节点创建一个从节点。

分配原则尽量保证**每个主数据库运行在不同的IP地址，每个从库和主库不在一个IP地址上**。



#### 9) 什么是 slots

合成集群成功后 加载的信息：

```
[OK] All nodes agree about slots configuration.
>>> Check for open slots...
>>> Check slots coverage...
[OK] All 16384 slots covered.			//<--最后一行信息
```

一个 Redis 集群包含 **16384** 个插槽（hash slot）， 数据库中的每个键都属于这 16384 个插槽的其中一个， 

集群使用公式 **CRC16(key) % 16384** 来计算键 key 属于哪个槽， 其中 CRC16(key) 语句用于计算键 key 的 CRC16 校验和 。

集群中的每个节点负责处理一部分插槽。 举个例子， 如果一个集群可以有3个主节点， 其中：

- 节点 A 负责处理 0 号至 5460 号插槽。

- 节点 B 负责处理 5461 号至 10922 号插槽。

- 节点 C 负责处理 10923 号至 16383 号插槽。

	

在查看集群信息的时候我们可以看到，每个主节点所负责处理的插槽：

![1675496201578](E:\redis6笔记\images\1675496201578.png)



#### 10) 在集群中录入值



(1) `redis-cli -p 6379` 登录后 每次录入、查询键值，redis都会计算出该key应该送往的插槽，如果不是该客户端对应服务器的插槽，redis会报错，并告知应前往的redis实例地址和端口。



(2) **redis-cli客户端提供了 –c 参数实现自动重定向。**

`redis-cli -c –p 6379` 登入后，再录入、查询键值对，经过计算 如果不是6379管理的插槽 可Redirecte自动重定向 到对应插槽中。

假如在 6379中 `set k1 v1` 经过计算 k1的CRC16校验和为12706，就会将它自动重定向到 6381管理的插槽中，并且 会切换到6381中 方便你下一次命令的执行

![1675496742329](E:\redis6笔记\images\1675496742329.png)



不在一个slot下的键值，是不能使用mget,mset等多键操作。

![1675354762556](E:\redis6笔记\images\1675354762556.png)



可以通过{}来定义组的概念，从而使key中{}内相同内容的键值对放到一个slot中去。

因为他会 根据组的名字(cust)去 计算插槽

![1675354776485](E:\redis6笔记\images\1675354776485.png)



#### 11) 查询集群中的值

`cluster keyslot key/组名` 查询 key/组名 存储在那个slot插槽中

`cluster countkeysinslot <slot> ` 查询 slot槽中 有几个 key

`cluster getkeysinslot <slot> <count>` 返回 slot槽中 count 个 key。

![1675354802922](E:\redis6笔记\images\1675354802922.png)

**注意**：上面的命令只能查询自己管理的插槽，比如6379就只能查询 0-5460插槽中的key



#### 12) 故障恢复

(1) 如果主节点下线？从节点能否自动升为主节点？能自动升为主机 

​	注意：**15秒超时**

![1675354826367](E:\redis6笔记\images\1675354826367.png)

(2) 主节点恢复后，主从关系会如何？主节点回来变成从机。

![1675354840841](E:\redis6笔记\images\1675354840841.png)

(3) 如果所有某一段插槽的主从节点都宕掉，redis服务是否还能继续? 

​    取决于redis.conf 中的参数  cluster-require-full-coverage

- cluster-require-full-coverage 为 yes ，那么，整个集群都挂掉

- cluster-require-full-coverage 为 no ，那么，该插槽数据全都不能使用，也无法存储，但是其它的主机插槽可以提供服务。





#### 13) 集群的Jedis开发

即使连接的不是主机，集群会自动切换主机存储。主机写，从机读。

无中心化主从集群。**无论从哪台主机写的数据，其他主机上都能读到数据**。

```java
public class JedisClusterTest {
  	public static void main(String[] args) { 
        
        //创建 JedisCluster
     	Set<HostAndPort> set =new HashSet<HostAndPort>();
        HostAndPort hostAndPort = new HostAndPort("192.168.200.130",6379)
     	set.add(hostAndPort);
     	JedisCluster jedisCluster=new JedisCluster(set);
        
        //进行操作
     	jedisCluster.set("k1", "v1");
    	System.out.println(jedisCluster.get("k1"));
 	 }
}
```



#### 14) Redis 集群提供了以下好处

实现扩容

分摊压力

无中心配置相对简单



#### 15) Redis 集群的不足

多键操作是不被支持的 

多键的Redis事务是不被支持的。lua脚本不被支持

由于集群方案出现较晚，很多公司已经采用了其他的集群方案，而代理或者客户端分片的方案想要迁移至redis cluster，需要整体迁移而不是逐步过渡，复杂度较大。







### 15. Redis应用问题解决



#### 1) 缓存穿透

##### (1)  问题描述

key对应的数据在数据源并不存在，每次针对此key的请求从缓存获取不到，请求都会压到数据源，从而可能压垮数据源。比如用一个不存在的用户id获取用户信息，不论缓存还是数据库都没有，若黑客利用此漏洞进行攻击可能压垮数据库。

![1675355121270](E:\redis6笔记\images\1675355121270.png)



##### (2) 解决方案

一个一定不存在缓存及查询不到的数据，由于缓存是不命中时被动写的，并且出于容错考虑，如果从存储层查不到数据则不写入缓存，这将导致这个不存在的数据每次请求都要到存储层去查询，失去了缓存的意义。

解决方案：

- **对空值缓存：**

	如果一个查询返回的数据为空（不管是数据是否不存在），我们仍然把这个空结果（null）进行缓存，空结果的过期时间设置短一些，最长不超过五分钟

- **设置可访问的名单（白名单）：**

	使用 bitmaps类型定义一个可以访问的名单，名单id作为bitmaps的偏移量，每次访问和bitmap里面的id进行比较，如果访问id不在bitmaps里面，进行拦截，不允许访问。

- **采用布隆过滤器**：

	布隆过滤器（Bloom Filter）是1970年由布隆提出的。它实际上是一个很长的二进制向量(位图)和一系列随机映射函数（哈希函数）。

	布隆过滤器可以用于检索一个元素是否在一个集合中。它的优点是空间效率和查询时间都远远超过一般的算法，缺点是有一定的误识别率和删除困难。

	将所有可能存在的数据哈希到一个足够大的bitmaps中，一个一定不存在的数据会被 这个bitmaps拦截掉，从而避免了对底层存储系统的查询压力。

- **进行实时监控：**

	当发现Redis的命中率开始急速降低，需要排查访问对象和访问的数据，和运维人员配合，可以设置黑名单限制服务





#### 2) 缓存击穿



##### (1) 问题描述

key对应的数据存在，但在redis中过期，此时若有大量并发请求过来，这些请求发现缓存过期一般都会从后端DB加载数据并回设到缓存，这个时候大并发的请求可能会瞬间把后端DB压垮。

![1675355276332](E:\redis6笔记\images\1675355276332.png)

##### (2) 解决方案

​    key可能会在某些时间点被超高并发地访问，是一种非常“热点”的数据。这个时候，需要考虑一个问题：缓存被“击穿”的问题。

解决问题：

**（1）预先设置热门数据：**在redis高峰访问之前，把一些热门数据提前存入到redis里面，加大这些热门数据key的时长

**（2）实时调整：**现场监控哪些数据热门，实时调整key的过期时长

**（3）使用锁：**

- 就是在缓存失效的时候（判断拿出来的值为空），不是立即去 访问db。

- 先使用缓存工具的某些带成功操作返回值的操作

	比如Redis的 SETNX设置锁 `setnx key_mutex 1 ` 

- 当操作返回成功时，再进行 访问db的操作，并回设缓存,最后删除锁 `del key_mutex` ；

- 当操作返回失败，证明有线程在访问db，当前线程睡眠一段时间再重试整个get缓存的方法。

	

	大致流程：设置互斥锁。在并发的多个请求中，只有第一个请求线程能拿到锁并执行数据库查询操作，其他的线程拿不到锁就阻塞等着，等到第一个线程将数据写入缓存后，直接走缓存。

![1675355346302](E:\redis6笔记\images\1675355346302.png)



#### 3) 缓存雪崩



##### (1) 问题描述

key对应的数据存在，但在redis中过期，此时若有大量并发请求过来，这些请求发现缓存过期一般都会从后端DB加载数据并回设到缓存，这个时候大并发的请求可能会瞬间把后端DB压垮。

缓存雪崩与缓存击穿的区别在于这里针对**极少时间很多key缓存**，前者则是**某一个热门key**

正常访问

![1675355404506](E:\redis6笔记\images\1675355404506.png)



缓存失效瞬间

![1675355414785](E:\redis6笔记\images\1675355414785.png)





##### (2) 解决方案

缓存失效时的雪崩效应对底层系统的冲击非常可怕！

解决方案：

- **构建多级缓存架构：**

	nginx缓存 + redis缓存 +其他缓存（ehcache等）

- **使用锁或队列**：

	用加锁或者队列的方式保证来保证不会有大量的线程对数据库一次性进行读写，从而避免失效时大量的并发请求落到底层存储系统上。但不适用高并发情况

- **设置过期标志更新缓存：**

	记录缓存数据是否过期（设置提前量），如果过期会触发通知另外的线程在后台去更新实际key的缓存。

- **将缓存失效时间分散开：**

	比如我们可以在原有的失效时间基础上增加一个随机值，比如1-5分钟随机，这样每一个缓存的过期时间的重复率就会降低，就很难引发集体失效的事件。



#### 4) 分布式锁

##### (1) 问题描述

随着业务发展的需要，原单体单机部署的系统被演化成分布式集群系统后，由于分布式系统多线程、多进程并且分布在不同机器上，这将使原单机部署情况下的并发控制锁策略失效，单纯的Java API并不能提供分布式锁的能力。为了解决这个问题就需要一种跨JVM的互斥机制来控制共享资源的访问，这就是分布式锁要解决的问题！

分布式锁主流的实现方案：

- 基于数据库实现分布式锁
- 基于缓存（Redis等）
- 基于 Zookeeper

每一种分布式锁解决方案都有各自的优缺点：

- 性能：redis最高
- 可靠性：zookeeper最高

这里，我们就基于redis实现分布式锁。



##### (2) 解决方案：使用redis实现分布式锁

redis命令 `set sku:1:info “OK” NX PX 10000` 获取锁（设置锁）并设置过期时间

EX second ：设置键的过期时间为 second 秒 `SET key value EX second` 等同于 `SETEX key second value` 

PX millisecond ：设置键的过期时间为 millisecond 毫秒 `SET key value PX millisecond` 等同于 `PSETEX key millisecond value` 

NX ：只在键不存在时，才对键进行设置操作 `SET key value NX` 等同于 `SETNX key value` 。

XX ：只在键已经存在时，才对键进行设置操作  `SET key value XX` 等同于 `SETXX key value` 。

![1675355607031](E:\redis6笔记\images\1675355607031.png)



- 多个客户端同时获取锁（setnx）
- 获取成功，执行业务逻辑（从db获取数据，放入缓存），执行完成释放锁（del key）
- 其他客户端等待重试





##### (3) 在java中模拟这个过程

Redis先执行命令: `set num 0`

演示的业务逻辑就是对 num的值++

```java
@RestController
@RequestMapping("/redisTest")
public class RedisTestController {

    @Autowired
    private RedisTemplate redisTemplate;   

	@GetMapping("testLock")
    public void testLock(){
        //1获取锁（setnx设置锁）
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "111");
        //2获取锁成功、查询num的值
        if(lock){
            Object value = redisTemplate.opsForValue().get("num");
            //2.1判断num为空return
            if(StringUtils.isEmpty(value)){
                return;
            }
            //2.2有值就转成成int
            int num = Integer.parseInt(value+"");
            //2.3把redis的num加1
            redisTemplate.opsForValue().set("num", ++num);
            //2.4释放锁，del
            redisTemplate.delete("lock");

        }else{
            //3获取锁失败、每隔0.1秒再获取
            try {
                Thread.sleep(100);
                testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
```



重启，服务集群，通过网关压力测试（ab测试）：

ab -n 5000 -c 100 http://192.168.140.1:8080/test/testLock

![1675355711725](E:\redis6笔记\images\1675355711725.png)

查看redis中num的值：

![1675355724862](E:\redis6笔记\images\1675355724862.png)



**假设遇到问题**：setnx刚好获取到锁，业务逻辑出现异常，导致锁无法释放

**解决**：设置过期时间，自动释放锁。







##### (4) 优化之设置锁的过期时间



设置过期时间有两种方式：

- 首先想到通过expire设置过期时间（缺乏原子性：如果在setnx和expire之间出现异常，锁也无法释放）

- 在set时指定过期时间（推荐）

![1675355776807](E:\redis6笔记\images\1675355776807.png)



设置过期时间：

```java
//1获取锁（setnx设置锁,并设置3秒过期时间）
Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "111", 3, TimeUnit.SECONDS);
```

![1675355787914](E:\redis6笔记\images\1675355787914.png)

压力测试肯定也没有问题。自行测试



**问题**：可能会释放其他服务器的锁。

场景：如果业务逻辑的执行时间是7s，设置的锁过期时间为3s。

执行流程如下

- index1业务逻辑**没执行完**，3秒后锁被自动释放。

- index2获取到锁，执行业务逻辑，3秒后锁被自动释放。

- index3获取到锁，执行业务逻辑

- index1业务逻辑执行完成，开始调用del释放锁，这时释放的是index3的锁，导致index3的业务只执行1s就被别人释放。

最终等于没锁的情况。

**解决**：setnx获取锁时，设置一个指定的唯一值（例如：uuid）；释放前获取这个值，判断是否自己的锁



##### 5) 优化之UUID防误删

![1675355860579](E:\redis6笔记\images\1675355860579.png)





![1675355876413](E:\redis6笔记\images\1675355876413.png)



**问题：**删除操作缺乏原子性。

场景：如果业务逻辑的执行时间是7s，设置的锁过期时间7s 

- index1开始比较uuid和锁的值，比较成功，**但是刚要去删除锁的时候，lock过期时间已到**，被redis自动释放了

- 此时index2获取了lock

	index2线程获取到了cpu的资源，开始执行方法的时候

- index1要开始执行删除锁，此时还会把index2的lock删除

	index1 因为已经在方法中了，所以不需要重新上锁。index1有执行的权限。并且index1已经完成uuid和锁的比较了，这个时候，开始执行

	删除的index2的锁！

**解决：**删除的时候使用lua脚本 保证原子性

##### (6) 优化之LUA脚本保证删除的原子性

```java
@GetMapping("testLockLua")
public void testLockLua() {
    //1 声明一个uuid ,将做为一个value 放入我们的key所对应的值中
    String uuid = UUID.randomUUID().toString();
    //2 定义一个锁：lua 脚本可以使用同一把锁，来实现删除！
    String skuId = "25"; // 访问skuId 为25号的商品 100008348542
    String locKey = "lock:" + skuId; // 锁住的是每个商品的数据

    // 3 获取锁
    Boolean lock = redisTemplate.opsForValue().setIfAbsent(locKey, uuid, 3, TimeUnit.SECONDS);

    // 第一种： lock 与过期时间中间不写任何的代码。
    // redisTemplate.expire("lock",10, TimeUnit.SECONDS);//设置过期时间
    // 如果true
    if (lock) {
        // 执行的业务逻辑开始
        // 获取缓存中的num 数据
        Object value = redisTemplate.opsForValue().get("num");
        // 如果是空直接返回
        if (StringUtils.isEmpty(value)) {
            return;
        }
        // 不是空 如果说在这出现了异常！ 那么delete 就删除失败！ 也就是说锁永远存在！
        int num = Integer.parseInt(value + "");
        // 使num 每次+1 放入缓存
        redisTemplate.opsForValue().set("num", String.valueOf(++num));
        
        /*使用lua脚本来锁*/
        // 定义lua 脚本
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        // 使用redis执行lua执行
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(script);
        // 设置一下返回值类型 为Long
        // 因为删除判断的时候，返回的0,给其封装为数据类型。如果不封装那么默认返回String 类型，
        // 那么返回字符串与0 会有发生错误。
        redisScript.setResultType(Long.class);
        // 第一个要是script脚本 ，第二个需要判断的key，第三个就是key所对应的值。
        redisTemplate.execute(redisScript, Arrays.asList(locKey), uuid);
    } else {
        // 其他线程等待
        try {
            // 睡眠
            Thread.sleep(1000);
            // 睡醒了之后，调用方法。
            testLockLua();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```



Lua 脚本详解：

![1675356213913](E:\redis6笔记\images\1675356213913.png)



项目中正确使用：

```java
1.定义key，key应该是为每个sku定义的，也就是每个sku有一把锁。
String locKey ="lock:"+skuId; // 锁住的是每个商品的数据
Boolean lock = redisTemplate.opsForValue().setIfAbsent(locKey, uuid,3,TimeUnit.SECONDS);
```



##### (7) 总结



**加锁**

```java
// 1. 从redis中获取锁,set k1 v1 px 20000 nx
String uuid = UUID.randomUUID().toString();
Boolean lock = this.redisTemplate.opsForValue().setIfAbsent("lock", uuid, 2, TimeUnit.SECONDS);
```



**使用lua释放锁**

```java
// 2. 释放锁 del
String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
// 设置lua脚本返回的数据类型
DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
// 设置lua脚本返回类型为Long
redisScript.setResultType(Long.class);
redisScript.setScriptText(script);
redisTemplate.execute(redisScript, Arrays.asList("lock"),uuid);
```



**重试**

```java
Thread.sleep(500);
testLock();
```



为了确保分布式锁可用，我们至少要确保锁的实现同时**满足以下四个条件**：

- 互斥性。在任意时刻，只有一个客户端能持有锁。
- 不会发生死锁。即使有一个客户端在持有锁的期间崩溃而没有主动解锁，也能保证后续其他客户端能加锁。
- 解铃还须系铃人。加锁和解锁必须是同一个客户端，客户端自己不能把别人加的锁给解了。
- 加锁和解锁必须具有原子性。



#### 5) 总结

##### 缓存穿透

产生条件：

- 应用服务器压力变大了
- redis命中率降低
- 一直查询数据库

现象：

- redis查询不到数据库
- 出现非正常URL访问

解决方案：

- **对空值缓存**：如果一个查询返回的数据为空(不管是数据是否不存在)，我们仍然把这个空结果( null )进行缓存，设置空结果的过期时间会很短，最长不超过五分钟。
- **设置可访问的名单(白名单)** :
  使用bitmaps类型定义一个可以访问的名单，名单id作为bitmaps的偏移量，每次访问和bitmap里面的id进行比较，如果访问id不在bitmaps里面，进行拦截，不允许访问。
- **采用布隆过滤器**： (布隆过滤器(Bloom Filter)是1970年由布隆提出的。它实际上是一个很长的二进制向量(位图)和一系列随机映射函数(哈希函数)。布隆过滤器可以用于检索一个元素是否在一个集合中。 它的优点是空间效率和查询时间都远远超过一般的算法，缺点是有一定的误识别率和删除困难。
  将所有可能存在的数据哈希到一个足够大的bitmaps 中，一个一定不存在的数据会被这个bitmaps拦截掉，从而避免了对底层存储系统的查询压力。
- **进行实时监控**：当发现Redis的命中率开始急速降低，需要排查访问对象和访问的数据，和运维人员配合，可以设置黑名单限制服务。

##### 缓存击穿

产生条件：

- redis某个key过期了，大量访问使用这个key

现象：

- 数据库访问压力瞬时增加
- redis里面没有出现大量key过期
- redis正常运行

解决方案;

- **预先设置热门数据**：在redis高峰访问之前，把一些热门数据提前存入到redis里面，加大这些热门数据key的时长。
- **实时调整**：现场监控哪些数据热门，实时调整key的过期时长。
- **使用锁**：
  - 就是在缓存失效的时候(判断拿出来的值为空) ，不是立即去load db。 
  - 先使用缓存工具的某些带成功操作返回值的操作(比如Redis的SETNX )去set一个mutex key
  - 当操作返回成功时，再进行load db的操作，并回设缓存，最后删除mutex key
    当操作返回失败，证明有线程在load db，当前线程睡眠一段时间再重试整个get缓存的方法。

##### 缓存雪崩

产生条件：

- 在极少时间段，查询大量key的集合过期情况

现象：

- 数据库压力变大造成服务器崩溃

解决方案：

- **构建多级缓存架构**：nginx缓存+ redis缓存+其他缓存( ehcache等)。
- **使用锁或队列**：
  用加锁或者队列的方式来保证不会有大量的线程对数据库一次性进行读写，从而避免失效时大量的并发请求落到底层存储系统上。不适用高并发情况
- **设置过期标志更新缓存**：
  记录缓存数据是否过期(设置提前量)，如果过期会触发通知另外的线程在后台去更新实际key的缓存。
- **将缓存失效时间分散开**：
  比如我们可以在原有的失效时间基础上增加一个随机值，比如1-5 分钟随机，这样每一个缓存的过期时间的重复率就会降低，就很难引发集体失效的事件。



**分布式锁**

随着业务发展的需要，原单体单机部署的系统被演化成分布式集群系统后，由于分布式系统多线程、多进程并且分布在不同机器上，这将使原单机部署情况下的并发控制锁策略失效，单纯的Java API并不能提供分布式锁的能力。为了解决这个问题就需要一种跨JVM的互斥机制来控制共享资源的访问，这就是分布式锁要解决的问题。

**使用redis实现分布式锁**

- setnx上锁，del释放锁
- 锁一直没释放，设置过期时间，自动释放
- 上锁之后突然出现异常，无法设置过期时间了。

```shell
set users 10 nx ex 12
```

```java
setIfAbsent();
```

**锁可能会被别人释放，卡顿造成key过期自动释放。**

怎么避免：

- 第一步set lock uuid nx ex 10
- 第二步释放锁的时候，首先判断当前uuid和要释放锁uuid是否一样

**lua脚本保证删除原子性**

为了确保分布式锁可用，我们至少要确保锁的实现同时满足以下四个条件:

- 互斥性。在任意时刻，只有一个客户端能持有锁。
- 不会发生死锁。即使有一个客户端在持有锁的期间崩溃而没有主动解锁，也
  能保证后续其他客户端能加锁。
- 解铃还须系铃人。加锁和解锁必须是同一个客户端，客户端自己不能把别人加的锁给解了。
- 加锁和解锁必须具有原子性。





### 16. Redis6.0新功能



#### 1) ACL



##### (1) 简介

Redis ACL是Access Control List（访问控制列表）的缩写，该功能允许根据可以执行的命令和可以访问的键来限制某些连接。

在Redis 5版本之前，Redis 安全规则只有密码控制 还有通过rename 来调整高危命令比如 flushdb ， KEYS* ， shutdown 等。Redis 6 则提供ACL的功能对用户进行更细粒度的权限控制 ：

（1）接入权限:用户名和密码 

（2）可以执行的命令 

（3）可以操作的 KEY

参考官网：https://redis.io/topics/acl



##### (2) 命令

1、使用acl list命令展现用户权限列表

（1）数据说明

![1675389350112](E:\redis6笔记\images\1675389350112.png)

2、使用 acl cat 命令

（1）查看添加权限指令类别

![1675389364429](E:\redis6笔记\images\1675389364429.png)

(2) 加参数类型名可以查看类型下具体命令

![1675389391381](E:\redis6笔记\images\1675389391381.png)



3、使用 acl whoami 命令查看当前用户

![1675389408368](E:\redis6笔记\images\1675389408368.png)



4、使用 acl setuser 命令创建和编辑用户ACLzz

(1) ACL规则

下面是有效ACL规则的列表。某些规则只是用于激活或删除标志，或对用户ACL执行给定更改的单个单词。其他规则是字符前缀，它们与命令或类别名称、键模式等连接在一起。

![1675389442634](E:\redis6笔记\images\1675389442634.png)



(2) 通过命令创建新用户默认权限

acl setuser user1

![1675389456352](E:\redis6笔记\images\1675389456352.png)

在上面的示例中，我根本没有指定任何规则。如果用户不存在，这将使用just created的默认属性来创建用户。如果用户已经存在，则上面的命令将不执行任何操作。

 

(3) 设置有用户名、密码、ACL权限、并启用的用户

acl setuser user2 on >password ~cached:* +get

![1675389477502](E:\redis6笔记\images\1675389477502.png)



(4) 切换用户，验证权限

![1675389496612](E:\redis6笔记\images\1675389496612.png)



#### 2) IO多线程



##### (1) 简介

Redis6终于支撑多线程了，告别单线程了吗？

IO多线程其实指**客户端交互部分**的**网络IO**交互处理模块**多线程**，而非**执行命令多线程**。**Redis6执行命令依然是单线程**。

 

##### (2) 原理架构

Redis 6 加入多线程,但跟 Memcached 这种从 IO处理到数据访问多线程的实现模式有些差异。**Redis 的多线程部分只是用来处理网络数据的读写和协议解析，执行命令仍然是单线程**。之所以这么设计是不想因为多线程而变得复杂，需要去控制 key、lua、事务，LPUSH/LPOP 等等的并发问题。整体的设计大体如下:

![1675389559472](E:\redis6笔记\images\1675389559472.png)

另外，多线程IO默认也是不开启的，需要再配置文件中配置

io-threads-do-reads  yes 

io-threads 4



#### 3) 工具支持 Cluster

之前老版Redis想要搭集群需要单独安装ruby环境，Redis 5 将 redis-trib.rb 的功能集成到 redis-cli 。另外官方 redis-benchmark 工具开 

![1675389593763](E:\redis6笔记\images\1675389593763.png)



#### 4) Redis新功能持续关注

Redis6新功能还有：

1、RESP3新的 Redis 通信协议：优化服务端与客户端之间通信

2、Client side caching客户端缓存：基于 RESP3 协议实现的客户端缓存功能。为了进一步提升缓存的性能，将客户端经常访问的数据cache到客户端。减少TCP网络交互。

3、Proxy集群代理模式：Proxy 功能，让 Cluster 拥有像单实例一样的接入方式，降低大家使用cluster的门槛。不过需要注意的是代理不改变 Cluster 的功能限制，不支持的命令还是不会支持，比如跨 slot 的多Key操作。

4、Modules API

Redis 6中模块API开发进展非常大，因为Redis Labs为了开发复杂的功能，从一开始就用上Redis模块。Redis可以变成一个框架，利用Modules来构建不同系统，而不需要从头开始写然后还要BSD许可。Redis一开始就是一个向编写各种系统开放的平台。

