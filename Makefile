mysql:
	docker run -d \
	--name mysql-container \
  	-e MYSQL_ROOT_PASSWORD=root \
  	-e MYSQL_DATABASE=employee_db \
  	-e MYSQL_PASSWORD=root \
  	-p 3306:3306 \
  	mysql:5.7 --default-authentication-plugin=mysql_native_password