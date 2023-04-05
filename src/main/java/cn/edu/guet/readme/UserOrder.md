<!-- @Author DavidNan -->
<!-- @Date 2023-04-23 00:38:08 -->

(UserOrder)接口文档
===

1.分页查询
---
**接口地址：**
`/userOrder/queryByPage`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | size | long | 每页显示条数，默认 10 | | current | long | 当前页，默认是1 | | id |
Integer | ${column.comment} | | money | Integer | ${column.comment} | | amount | Integer | ${column.comment} | | userId
| Integer | ${column.comment} | | states | String | ${column.comment} | | createTime | Date | ${column.comment} | |
updateTime | Date | ${column.comment} |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | ${column.comment} | | money | Integer |
${column.comment} | | amount | Integer | ${column.comment} | | userId | Integer | ${column.comment} | | states | String
| ${column.comment} | | createTime | Date | ${column.comment} | | updateTime | Date | ${column.comment} |



2.根据ID查详情
---
**接口地址：**
`/userOrder/queryById`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | 主键 |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | ${column.comment} | | money | Integer |
${column.comment} | | amount | Integer | ${column.comment} | | userId | Integer | ${column.comment} | | states | String
| ${column.comment} | | createTime | Date | ${column.comment} | | updateTime | Date | ${column.comment} |



3.新增数据
---
**接口地址：**
`/userOrder/insert`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | ${column.comment} | | money | Integer |
${column.comment} | | amount | Integer | ${column.comment} | | userId | Integer | ${column.comment} | | states | String
| ${column.comment} | | createTime | Date | ${column.comment} | | updateTime | Date | ${column.comment} |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | data | int | 操作成功影响的行数 |

4.编辑数据
---
**接口地址：**
`/userOrder/update`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | ${column.comment} | | money | Integer |
${column.comment} | | amount | Integer | ${column.comment} | | userId | Integer | ${column.comment} | | states | String
| ${column.comment} | | createTime | Date | ${column.comment} | | updateTime | Date | ${column.comment} |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | data | int | 操作成功影响的行数 |

5.删除数据
---
**接口地址：**
`/userOrder/deleteById`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | 主键 |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | data | int | 操作成功影响的行数 | 
