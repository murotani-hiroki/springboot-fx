# 環境構築手順

### PostgreSQLコンテナ作成〜起動
`
$ docker run --name postgres -e POSTGRES_PASSWORD=muro1968 -p 15432:5432 -v psql_volume:/var/lib/postgresql/data -d postgres:latest
`

### DB／テーブル作成〜初期データインポート
`
$ psql -U postgres -h 127.0.0.1 -p 15432 fxdb -f /Users/hmurotani/Documents/workspace/springboot-fx/src/main/resources/ddl.sql
`

