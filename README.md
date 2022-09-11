# 🙌 공공 와이파이 서비스

사용자 위치를 기반으로 서울시 내 공공 와이파이 정보를 제공하는 웹 서비스


## 🙋‍♀️ 프로젝트 세부 정보
- Intellij Ultimate
  * Template : Web Application
  * Application server : Tomcat 9.0.65
  * Language : JAVA (JDK 1.8)
  * Build System : Maven
- 사용 라이브러리
  * mariadb-java-client-3.0.7
  * json-simple-1.1.1
- MariaDB
- [서울시 공공 와이파이 서비스 Open Api](https://data.seoul.go.kr/dataList/OA-20883/S/1/datasetView.do)

## 🛠 기능 엿보기   

1. 서울시 오픈 api 활용하여 공공 와이파이 정보 가져오기

2. 위치 정보 직접 입력 or 사용자 위치 가져오는 기능 구현

3. 사용자 위치 정보 활용하여 가까운 위치에 있는 와이파이 정보 20개 목록을 페이지에 불러오기

4. 위치 정보 조회 시 해당 위치 정보와 조회 시각 담은 히스토리를 DB에 저장 및 페이지에 불러오기

5. 페이지에 불러온 히스토리 개별 삭제 기능 구현
