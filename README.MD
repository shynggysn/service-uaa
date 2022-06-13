1) с проекта dkr-template взять шаблон
2) перенести код
3) убрать не совместимость createQuery jpa ? на  ?1
4) FileUpload fix with MultiPartFile, filename=getOriginalFileName()
5) Конфиги вытащить
6) Не совместимость с lowercase username keycloak
7) SEND EMAIL request делать вызов на новый EMAILSERVICE
8) в проектах entity добавить schemas
11)-Djava.awt.headless=true там где jasper ++ load resources from ResourceLoader + library digest
9) В @Entity добавить информацию про Schemas
10) Использовать CommonLibs для models, services

POSTGRES улучшение
CREATE INDEX idx_user_username on user_entity(username);
CREATE INDEX idx_attribute_user_name on user_attribute(user_id,name);


Долги
3) скрыть internal methods
4) common libs to nexus
5) dkr-clientswork изменть hostname edifactservice
6) dkr_ggu12 изменить host name-ы в soap calls
9) http.authorizeRequests().antMatchers("/servlet/**").authenticated().anyRequest().permitAll(); на всех проектах



WSDL LIBS


        <dependency>
            <groupId>ne.railways.fdu92</groupId>
            <artifactId>commons-beanutils-1.5.jar</artifactId>
            <version>1.0</version>
        </dependency>
        <dependency>
            <groupId>ne.railways.fdu92</groupId>
            <artifactId>commons-collections-3.1.jar</artifactId>
            <version>1.0</version>
        </dependency>
        <dependency>
            <groupId>ne.railways.fdu92</groupId>
            <artifactId>commons-discovery-0.2.jar</artifactId>
            <version>1.0</version>
        </dependency>
        <dependency>
            <groupId>javax.xml.soap</groupId>
            <artifactId>javax.xml.soap-api</artifactId>
            <version>1.4.0</version>
        </dependency>
        <dependency>
            <groupId>wsdl4j</groupId>
            <artifactId>wsdl4j</artifactId>
        </dependency>
		
        <dependency>
            <groupId>org.apache.axis</groupId>
            <artifactId>axis-jaxrpc</artifactId>
            <version>1.4</version>
        </dependency>

        <dependency>
            <groupId>ne.railways.fdu92</groupId>
            <artifactId>axis.jar</artifactId>
            <version>1.0</version>
        </dependency>