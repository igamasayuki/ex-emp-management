# 事前の整理

- [事前の整理](#事前の整理)
  - [domain](#domain)
    - [Administrator](#administrator)
    - [Employee](#employee)
  - [form](#form)
    - [InsertAdministratorForm](#insertadministratorform)
    - [LoginForm](#loginform)
    - [UpdateEmployeeForm](#updateemployeeform)
  - [repository](#repository)
    - [AdministratorRepository](#administratorrepository)
    - [EmployeeRepository](#employeerepository)
  - [service](#service)
    - [AdministratorService](#administratorservice)
    - [EmployeeService](#employeeservice)
  - [controller](#controller)
    - [AdministratorController](#administratorcontroller)
    - [EmployeeController](#employeecontroller)

## domain
### Administrator

|||
--- | ---
| id | Integer
| name | String |
| mailAddress | String |
| password | String |


### Employee
|||
--- | ---
| id | Integer |
| name | String |
| image | String |
| gender | String |
| hireDate | LocalDate |
| mailAddress | String |
| zipCode | String |
| address | String |
| telephone | String |
| salary | Integer |
| characteristics | String |
| dependentsCount | Integer |



## form
### InsertAdministratorForm
||||
--- | --- | ---
| name | String | NotNull<br> NotBlank<br> NotEmpty<br> |
| mailAddress | String | NotNull<br> NotBlank<br> NotEmpty<br> |
| password | String | NotNull<br> NotBlank<br> NotEmpty<br> |

### LoginForm
||||
--- | --- | ---
| mailAddress | String | NotNull<br> NotBlank<br> NotEmpty<br> |
| password | String | NotNull<br> NotBlank<br> NotEmpty<br> |


### UpdateEmployeeForm


## repository

### AdministratorRepository
- @Repository
フィールド
- @Autowired
- NamedParameterJdbcTemplate 名前付きパラメータ
- RowMapperを定義
  
- insert(Administrator administrator) : void
管理者情報を挿入する
  - sqlを宣言
  - MapSqlParameterSource
  - sqlを実行(update)

- findByMailAddressAndPassword(mailAddress, password): Administrator  
ログイン押下時に、渡されたメールアドレスとパスワードで一致する管理者情報を取得する(存在しない場合はnullを返す)
  - sqlを宣言
  - MapSqlParameterSource
  - sqlを実行(query)    
  ヒットした場合
    - Administratorを生成し、取得した情報をセット
    - Administratorを返す  

  - ヒットしない場合、nullを返す

### EmployeeRepository
- @Repository

フィールド
- @Autowired
- NamedParameterJdbcTemplate 名前付きパラメータ
- RowMapperを定義

- findAll() : List<Employee>  
従業員情報を全件取得する
  - sqlを宣言
  - sqlを実行(query)
  - 結果をList<Employee>に詰める
  - nullの場合は、nullを返す
  - 結果を返す

- load(Integer id): Employee  
idに一致する従業員情報を取得する
  - sqlを宣言
  - MapSqlParameterSource
  - sqlを実行(query)
  - 結果をEmployeeに詰める
  - 結果を返す

- update(Integer id) : void  
idカラムをのぞいた全てのカラムを更新する。
更新対象はidで一致するもの
  - sqlを宣言
  - MapSqlParameterSource(id以外の全項目)
  - sqlを実行(update)

## service


### AdministratorService
@Service  
フィールド
- @Autowired
- AdministratorRepository administratorRepository

- insert(Administrator) : administratorRepository.insert(Administrator)
- findByMailAddressAndPassword(String mailAddress, String password) : administratorRepository.findByMailAddressAndPassword(mailAddress, password)
### EmployeeService
@Service  
フィールド
- @Autowired
- EmployeeRepository employeeRepository
- findAll(String name) : employeeRepository.findAll(name)
- load(Integer id) : employeeRepository.load(id)
- update(Employee employee) : employeeRepository.update(employee)

## controller

### AdministratorController
管理者登録画面コントローラー  
@RequestMapping("/")



### EmployeeController
