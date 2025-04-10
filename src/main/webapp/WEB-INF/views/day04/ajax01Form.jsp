<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기상청 단기예보</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
	table { border-collapse: collapse; width: 100%; }
	th, td { border: 1px solid #aaa; padding: 8px; text-align: center; }
	th { background-color: #f2f2f2; }
</style>
</head>
<body>
	<h2>기상청_단기예보(서울)</h2>
	<button id="btnXml">XML 데이터 불러오기</button>
	<button id="btnJson">JSON 데이터 불러오기</button>

	<div id="result"></div>

	<script>
	// 해석용 텍스트 사전
	const weatherExplain = {
		POP: { label: "강수확률", interpret: val => val + "% 비 올 확률입니다." },
		PTY: { label: "강수형태", interpret: val => ({"0":"없음", "1":"비", "2":"비/눈", "3":"눈", "4":"소나기"}[val] || "미상") },
		REH: { label: "습도", interpret: val => val + "% 습도입니다." },
		SKY: { label: "하늘상태", interpret: val => ({"1":"맑음", "3":"구름많음", "4":"흐림"}[val] || "미상") },
		T3H: { label: "3시간 기온", interpret: val => val + "℃ 예상 기온입니다." },
		TMN: { label: "일 최저기온", interpret: val => val + "℃ 오늘 아침 최저입니다." },
		TMX: { label: "일 최고기온", interpret: val => val + "℃ 오늘 낮 최고입니다." },
		WSD: { label: "풍속", interpret: val => val + "m/s 바람입니다." }
	};

	// XML 불러오기
	$("#btnXml").on("click", function() {
		$("#result").html("<p>XML 데이터 로딩 중 ...</p>");
		$.ajax({
			url: "/weatherXml",
			method: "post",
			dataType: "xml",
			success: function(response) {
				let table = "<table> ";
				table += "<thead><tr><th>예보일자</th><th>시간</th><th>항목</th><th>값</th><th>설명</th><th>해석</th></tr></thead>";
				table += "<tbody>";
				$(response).find("item").each(function() {
					let date = $(this).find("fcstDate").text();
					let time = $(this).find("fcstTime").text();
					let category = $(this).find("category").text();
					let fcstValue = $(this).find("fcstValue").text();

					let label = weatherExplain[category]?.label || category;
					let desc = weatherExplain[category]?.interpret(fcstValue) || "-";

					table += "<tr>";
					table += "<td>" + date + "</td>";
					table += "<td>" + time + "</td>";
					table += "<td>" + category + "</td>";
					table += "<td>" + fcstValue + "</td>";
					table += "<td>" + label + "</td>";
					table += "<td>" + desc + "</td>";
					table += "</tr>";
				});
				table += "</tbody></table>";

				$("#result").html(table);
			},
			error: function() {
				alert("XML 데이터 불러오기 실패");
			}
		});
	});

	// JSON 불러오기 (기본 구조만)
	$("#btnJson").on("click", function() {
		$("#result").html("<p>JSON 데이터 로딩 중 ...</p>");
		$.ajax({
			url: "/weatherJson",
			method: "post",
			dataType: "json",
			success: function(response) {
				//console.log("JSON 응답 결과:", response);
				let items=response.response.body.items.item;
				
				success: function(response) {
					let table = "<table>";
					table += "<thead><tr><th>예보일자</th><th>시간</th><th>항목</th><th>값</th><th>설명</th><th>해석</th></tr></thead>";
					table += "<tbody>";
					
					$.each(item.function(i, v){
						let date = v.fcstDate;
						let time = v.fcstTime;
						let category = v.category;
						let fcstValue = v.fcstValue;

						let label = weatherExplain[category]?.label || category;
						let desc = weatherExplain[category]?.interpret(fcstValue) || "-";
					}
						
					});
					
					
					table += "</tbody></table>";

					$("#result").html(table);
				
				
				$("#result").html("<pre>" + JSON.stringify(response, null, 2) + "</pre>");
			},
			error: function() {
				alert("JSON 데이터 불러오기 실패");
			}
		});
	});
	</script>
</body>
</html>
