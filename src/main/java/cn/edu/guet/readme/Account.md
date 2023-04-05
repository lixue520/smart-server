<!-- @Author DavidNan -->
<!-- @Date 2023-04-19 13:26:02 -->

(Account)接口文档
===

1.分页查询
---
**接口地址：**
`/account/queryByPage`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | size | long | 每页显示条数，默认 10 | | current | long | 当前页，默认是1 | | id |
Integer | 卡ID | | icStates | String | 卡状态 | | user_money | String | 余额 | | icIntegral | String | 积分 | | createTime |
Date | 建时间 | | updateTime | Date | 修改时间 | | user_id | Integer | ${column.comment} | | user_password | String |
${column.comment} |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | 卡ID | | icStates | String | 卡状态 | | user_money |
String | 余额 | | icIntegral | String | 积分 | | createTime | Date | 建时间 | | updateTime | Date | 修改时间 | | user_id | Integer
| ${column.comment} | | user_password | String | ${column.comment} |



2.根据ID查详情
---
**接口地址：**
`/account/queryById`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | 主键 |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | 卡ID | | icStates | String | 卡状态 | | user_money |
String | 余额 | | icIntegral | String | 积分 | | createTime | Date | 建时间 | | updateTime | Date | 修改时间 | | user_id | Integer
| ${column.comment} | | user_password | String | ${column.comment} |



3.新增数据
---
**接口地址：**
`/account/insert`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | 卡ID | | icStates | String | 卡状态 | | user_money |
String | 余额 | | icIntegral | String | 积分 | | createTime | Date | 建时间 | | updateTime | Date | 修改时间 | | user_id | Integer
| ${column.comment} | | user_password | String | ${column.comment} |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | data | int | 操作成功影响的行数 |

4.编辑数据
---
**接口地址：**
`/account/update`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | 卡ID | | icStates | String | 卡状态 | | user_money |
String | 余额 | | icIntegral | String | 积分 | | createTime | Date | 建时间 | | updateTime | Date | 修改时间 | | user_id | Integer
| ${column.comment} | | user_password | String | ${column.comment} |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | data | int | 操作成功影响的行数 |

5.删除数据
---
**接口地址：**
`/account/deleteById`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | 主键 |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | data | int | 操作成功影响的行数 | 
