<!-- @Author DavidNan -->
<!-- @Date 2023-04-16 16:07:53 -->

(UserRobot)接口文档
===

1.分页查询
---
**接口地址：**
`/userRobot/queryByPage`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | size | long | 每页显示条数，默认 10 | | current | long | 当前页，默认是1 | | id |
Integer | cz | | user_id | Integer | ${column.comment} | | robotId | Integer | ${column.comment} | | createTime | Date |
建时间 | | updateTime | Date | 修改时间 |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | cz | | user_id | Integer | ${column.comment} | |
robotId | Integer | ${column.comment} | | createTime | Date | 建时间 | | updateTime | Date | 修改时间 |



2.根据ID查详情
---
**接口地址：**
`/userRobot/queryById`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | 主键 |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | cz | | user_id | Integer | ${column.comment} | |
robotId | Integer | ${column.comment} | | createTime | Date | 建时间 | | updateTime | Date | 修改时间 |



3.新增数据
---
**接口地址：**
`/userRobot/insert`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | cz | | user_id | Integer | ${column.comment} | |
robotId | Integer | ${column.comment} | | createTime | Date | 建时间 | | updateTime | Date | 修改时间 |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | data | int | 操作成功影响的行数 |

4.编辑数据
---
**接口地址：**
`/userRobot/update`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | cz | | user_id | Integer | ${column.comment} | |
robotId | Integer | ${column.comment} | | createTime | Date | 建时间 | | updateTime | Date | 修改时间 |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | data | int | 操作成功影响的行数 |

5.删除数据
---
**接口地址：**
`/userRobot/deleteById`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | 主键 |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | data | int | 操作成功影响的行数 | 
