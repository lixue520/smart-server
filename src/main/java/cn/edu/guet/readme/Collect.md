<!-- @Author DavidNan -->
<!-- @Date 2023-04-19 13:18:16 -->

(Collect)接口文档
===

1.分页查询
---
**接口地址：**
`/collect/queryByPage`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | size | long | 每页显示条数，默认 10 | | current | long | 当前页，默认是1 | | id |
Integer | ${column.comment} | | ph | String | ${column.comment} | | tds | String | ${column.comment} | | temp | String |
${column.comment} | | flow1 | String | ${column.comment} | | flow | String | ${column.comment} | | height | String |
${column.comment} | | height1 | String | ${column.comment} | | bottle | String | ${column.comment} | | dzf | Integer |
${column.comment} | | dzf1 | Integer | ${column.comment} | | pump | Integer | ${column.comment} | | pump1 | Integer |
${column.comment} | | csb | String | ${column.comment} | | robotId | Integer | ${column.comment} | | createTime | Date |
建时间 | | updateTime | Date | 修改时间 | | user_id | Integer | ${column.comment} | | beep | Integer | ${column.comment} | |
zwLed | Integer | ${column.comment} | | types | String | ${column.comment} | | status | Integer | ${column.comment} | |
devicdId | String | ${column.comment} |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | ${column.comment} | | ph | String |
${column.comment} | | tds | String | ${column.comment} | | temp | String | ${column.comment} | | flow1 | String |
${column.comment} | | flow | String | ${column.comment} | | height | String | ${column.comment} | | height1 | String |
${column.comment} | | bottle | String | ${column.comment} | | dzf | Integer | ${column.comment} | | dzf1 | Integer |
${column.comment} | | pump | Integer | ${column.comment} | | pump1 | Integer | ${column.comment} | | csb | String |
${column.comment} | | robotId | Integer | ${column.comment} | | createTime | Date | 建时间 | | updateTime | Date | 修改时间 | |
user_id | Integer | ${column.comment} | | beep | Integer | ${column.comment} | | zwLed | Integer | ${column.comment} | |
types | String | ${column.comment} | | status | Integer | ${column.comment} | | devicdId | String | ${column.comment} |



2.根据ID查详情
---
**接口地址：**
`/collect/queryById`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | 主键 |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | ${column.comment} | | ph | String |
${column.comment} | | tds | String | ${column.comment} | | temp | String | ${column.comment} | | flow1 | String |
${column.comment} | | flow | String | ${column.comment} | | height | String | ${column.comment} | | height1 | String |
${column.comment} | | bottle | String | ${column.comment} | | dzf | Integer | ${column.comment} | | dzf1 | Integer |
${column.comment} | | pump | Integer | ${column.comment} | | pump1 | Integer | ${column.comment} | | csb | String |
${column.comment} | | robotId | Integer | ${column.comment} | | createTime | Date | 建时间 | | updateTime | Date | 修改时间 | |
user_id | Integer | ${column.comment} | | beep | Integer | ${column.comment} | | zwLed | Integer | ${column.comment} | |
types | String | ${column.comment} | | status | Integer | ${column.comment} | | devicdId | String | ${column.comment} |



3.新增数据
---
**接口地址：**
`/collect/insert`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | ${column.comment} | | ph | String |
${column.comment} | | tds | String | ${column.comment} | | temp | String | ${column.comment} | | flow1 | String |
${column.comment} | | flow | String | ${column.comment} | | height | String | ${column.comment} | | height1 | String |
${column.comment} | | bottle | String | ${column.comment} | | dzf | Integer | ${column.comment} | | dzf1 | Integer |
${column.comment} | | pump | Integer | ${column.comment} | | pump1 | Integer | ${column.comment} | | csb | String |
${column.comment} | | robotId | Integer | ${column.comment} | | createTime | Date | 建时间 | | updateTime | Date | 修改时间 | |
user_id | Integer | ${column.comment} | | beep | Integer | ${column.comment} | | zwLed | Integer | ${column.comment} | |
types | String | ${column.comment} | | status | Integer | ${column.comment} | | devicdId | String | ${column.comment} |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | data | int | 操作成功影响的行数 |

4.编辑数据
---
**接口地址：**
`/collect/update`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | ${column.comment} | | ph | String |
${column.comment} | | tds | String | ${column.comment} | | temp | String | ${column.comment} | | flow1 | String |
${column.comment} | | flow | String | ${column.comment} | | height | String | ${column.comment} | | height1 | String |
${column.comment} | | bottle | String | ${column.comment} | | dzf | Integer | ${column.comment} | | dzf1 | Integer |
${column.comment} | | pump | Integer | ${column.comment} | | pump1 | Integer | ${column.comment} | | csb | String |
${column.comment} | | robotId | Integer | ${column.comment} | | createTime | Date | 建时间 | | updateTime | Date | 修改时间 | |
user_id | Integer | ${column.comment} | | beep | Integer | ${column.comment} | | zwLed | Integer | ${column.comment} | |
types | String | ${column.comment} | | status | Integer | ${column.comment} | | devicdId | String | ${column.comment} |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | data | int | 操作成功影响的行数 |

5.删除数据
---
**接口地址：**
`/collect/deleteById`

**入参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | id | Integer | 主键 |

**回参：**
| 参数名称 | 参数类型 | 参数描述 | | :----: | :----: | :----: | | data | int | 操作成功影响的行数 | 
