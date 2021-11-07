# HadoopUdf-PrefixTree
不同项目的优先级不同，应当使用不同的集群。大数据元数据中包含每个作业的作业名和正在使用的资源池，本UDF使用已经存在的所有项目名称构建字典树，输入作业名称查找输出对应的项目名称，随后通过项目名称匹配项目维度表可以查找到作业应当使用的资源池，如果项目正在使用的资源池与匹配出来的资源池不同，则可以判定为资源池混用，随后通知整改。

## Install
JDK

## Usage
Java&Maven

## Contributing
Wei Fang(only)

## License

MIT © Wei Fang
