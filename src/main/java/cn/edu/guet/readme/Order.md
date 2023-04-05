<!-- @Author DavidNan -->
<!-- @Date 2023-04-23 00:07:02 -->

(Order)接口文档
===

1.分页查询
---
**接口地址：**
`/order/queryByPage`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | size | long | 每页显示条数，默认 10 | | current | long | 当前页，默认是1 | | id |
Integer | ${column.comment} | | amount | Integer | ${column.comment} | | money | String | ${column.comment} | | address
| String | ${column.comment} | | states | String | ${column.comment} | | createTime | Date | 建时间 | | updateTime | Date |
修改时间 | | userId | Integer | ${column.comment} |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | ${column.comment} | | amount | Integer |
${column.comment} | | money | String | ${column.comment} | | address | String | ${column.comment} | | states | String |
${column.comment} | | createTime | Date | 建时间 | | updateTime | Date | 修改时间 | | userId | Integer | ${column.comment} |



2.根据ID查详情
---
**接口地址：**
`/order/queryById`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | 主键 |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | ${column.comment} | | amount | Integer |
${column.comment} | | money | String | ${column.comment} | | address | String | ${column.comment} | | states | String |
${column.comment} | | createTime | Date | 建时间 | | updateTime | Date | 修改时间 | | userId | Integer | ${column.comment} |



3.新增数据
---
**接口地址：**
`/order/insert`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | ${column.comment} | | amount | Integer |
${column.comment} | | money | String | ${column.comment} | | address | String | ${column.comment} | | states | String |
${column.comment} | | createTime | Date | 建时间 | | updateTime | Date | 修改时间 | | userId | Integer | ${column.comment} |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | data | int | 操作成功影响的行数 |

4.编辑数据
---
**接口地址：**
`/order/update`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | ${column.comment} | | amount | Integer |
${column.comment} | | money | String | ${column.comment} | | address | String | ${column.comment} | | states | String |
${column.comment} | | createTime | Date | 建时间 | | updateTime | Date | 修改时间 | | userId | Integer | ${column.comment} |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | data | int | 操作成功影响的行数 |

5.删除数据
---
**接口地址：**
`/order/deleteById`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | 主键 |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | data | int | 操作成功影响的行数 | 
