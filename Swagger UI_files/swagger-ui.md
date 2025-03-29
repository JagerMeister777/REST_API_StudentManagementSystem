# 受講生管理システム APIドキュメント

受講生情報を管理するシステムです。受講生の個人情報と受講しているコース情報を管理し、CRUD操作を行えます。

## サーバー

- **URL**: [http://localhost:8080](http://localhost:8080)  
  **説明**: ローカル環境

## APIエンドポイント

### 1. `GET /api/students/{id}`
受講生IDで検索

- **概要**: 特定の受講生情報を受講生IDで検索します。IDが存在しなかったり、情報が削除されていた場合はエラーを発生させます。
- **パラメータ**:
    - `id` (path): 受講生ID（整数）
- **レスポンス**:
    - **200 OK**: 受講生情報を含むJSON (`StudentWithCoursesDto`形式)
    - **404 Not Found**: エラー情報（`ErrorResponse`形式）

### 2. `PUT /api/students/{id}`
受講生情報の更新

- **概要**: 特定の受講生情報の更新を行います。
- **パラメータ**:
    - `id` (path): 受講生ID（整数）
- **リクエストボディ**: `UpdateStudentWithCoursesDto`
- **レスポンス**:
    - **200 OK**: 更新後の受講生情報（`StudentWithCoursesDto`形式）
    - **400 Bad Request**: エラーメッセージ（`ErrorResponse`形式）

### 3. `DELETE /api/students/{id}`
受講生情報の論理削除

- **概要**: 受講生情報の論理削除を行います。
- **パラメータ**:
    - `id` (path): 受講生ID（整数）
- **レスポンス**:
    - **200 OK**: 削除完了メッセージ（文字列）
    - **404 Not Found**: エラー情報（`ErrorResponse`形式）

### 4. `GET /api/students`
受講生情報の全件取得

- **概要**: 受講生の個人情報と受講しているコース情報の全件取得を行います。
- **レスポンス**:
    - **200 OK**: 受講生情報のリスト（`StudentWithCoursesDto`形式）

### 5. `POST /api/students`
受講生情報の登録

- **概要**: 受講生情報を登録する処理を行います。
- **リクエストボディ**: `RegisterStudentWithCoursesDto`
- **レスポンス**:
    - **200 OK**: 登録した受講生情報（`StudentWithCoursesDto`形式）
    - **400 Bad Request**: フィールドエラー（`FieldsErrorResponse`形式）

## スキーマ

### Student
- **説明**: 受講生の個人情報を管理するクラス
- **プロパティ**:
    - `id`: 受講生ID (整数)
    - `fullName`: 氏名 (文字列)
    - `furigana`: フリガナ (文字列)
    - `nickName`: ニックネーム (文字列)
    - `email`: メールアドレス (文字列)
    - `livingArea`: 居住地域 (文字列)
    - `age`: 年齢 (整数)
    - `gender`: 性別 (文字列)
    - `remark`: 備考 (文字列)
    - `deleted`: 論理削除フラグ (ブール)

### StudentWithCoursesDto
- **説明**: 受講生情報とその受講しているコース情報を含むクラス
- **プロパティ**:
    - `student`: 受講生の個人情報（`Student`形式）
    - `studentsCourses`: 受講生のコース情報（`StudentsCoursesDetail`形式の配列）

### StudentsCoursesDetail
- **説明**: 受講生が受講しているコースの詳細
- **プロパティ**:
    - `courseName`: コース名 (文字列)
    - `courseStartDate`: 受講開始日 (日付)
    - `courseEndDate`: 受講終了日 (日付)

### ErrorResponse
- **説明**: リクエストエラーが発生した時のレスポンス
- **プロパティ**:
    - `status`: HTTPステータスコード (整数)
    - `error`: エラーの種類 (文字列)
    - `message`: エラーメッセージ (文字列)
    - `timeStamp`: エラー発生時のタイムスタンプ (文字列)

### UpdateStudentDto
- **説明**: 受講生の個人情報を更新する際に使用されるクラス
- **プロパティ**:
    - `id`: 受講生ID (整数)
    - `fullName`: 氏名 (文字列)
    - `furigana`: フリガナ (文字列)
    - `nickName`: ニックネーム (文字列)
    - `email`: メールアドレス (文字列)
    - `livingArea`: 居住地域 (文字列)
    - `age`: 年齢 (整数)
    - `gender`: 性別 (文字列)
    - `remark`: 備考 (文字列)

### UpdateStudentWithCoursesDto
- **説明**: 受講生情報と受講コース情報を更新する際に使用されるクラス
- **プロパティ**:
    - `student`: 受講生の個人情報（`UpdateStudentDto`形式）
    - `studentsCourses`: 受講するコース情報（`StudentsCoursesDetail`形式の配列）

### FieldsErrorResponse
- **説明**: フィールドエラーが発生した時のレスポンス
- **プロパティ**:
    - `status`: HTTPステータスコード (整数)
    - `error`: エラーの種類 (文字列)
    - `messageMap`: フィールドエラーメッセージ（オブジェクト形式）
    - `timeStamp`: エラー発生時のタイムスタンプ (文字列)

### RegisterStudentDto
- **説明**: 受講生情報を登録する際に使用されるクラス
- **プロパティ**:
    - `fullName`: 氏名 (文字列)
    - `furigana`: フリガナ (文字列)
    - `nickName`: ニックネーム (文字列)
    - `email`: メールアドレス (文字列)
    - `livingArea`: 居住地域 (文字列)
    - `age`: 年齢 (整数)
    - `gender`: 性別 (文字列)
    - `remark`: 備考 (文字列)

### RegisterStudentWithCoursesDto
- **説明**: 受講生情報とコース情報を登録する際に使用されるクラス
- **プロパティ**:
    - `student`: 受講生の個人情報（`RegisterStudentDto`形式）
    - `studentsCourses`: 受講するコース情報（`StudentsCoursesDetail`形式の配列）
