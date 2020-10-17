<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Election Description</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body style="background-image: url('wavybg.svg');">
<div style="
    height: 65px;
    margin: 0;
    padding: 0;
    background-image: linear-gradient(45deg, #6a6da1, #3f3d56);
"></div>

	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	
	
		if (session.getAttribute("uname") == null) {
			response.sendRedirect("login.jsp");
		}
	%>
	
	<svg class="element" id="b710ac8c-5016-430d-bef2-d7bc75088669" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" width="928.75816" height="567.73661" viewBox="0 0 928.75816 567.73661"><path d="M857.74388,569.26443a10.05579,10.05579,0,0,0,.80113-15.39851l14.4116-32.69992L854.648,524.26882l-10.87859,30.5767a10.11028,10.11028,0,0,0,13.9745,14.41891Z" transform="translate(-135.62092 -166.13169)" fill="#a0616a"/><path d="M885.32645,424.37639l-5.24589-1.04918s-7.34424,3.14754-6.29506,12.59013S859.097,495.72047,859.097,495.72047l-14.1639,41.9671,19.93438,4.19671,13.11472-48.26217Z" transform="translate(-135.62092 -166.13169)" fill="#ccc"/><path d="M909.12092,584.86831h-114a8.50982,8.50982,0,0,1-8.5-8.5v-11a8.50981,8.50981,0,0,1,8.5-8.5h114a8.51013,8.51013,0,0,1,8.5,8.5v11A8.51014,8.51014,0,0,1,909.12092,584.86831Z" transform="translate(-135.62092 -166.13169)" fill="#6c63ff"/><polygon points="766.749 539.503 755.248 543.748 733.399 501.407 750.374 495.141 766.749 539.503" fill="#a0616a"/><path d="M883.747,707.64942h23.64386a0,0,0,0,1,0,0v14.88687a0,0,0,0,1,0,0H868.86015a0,0,0,0,1,0,0v0A14.88686,14.88686,0,0,1,883.747,707.64942Z" transform="translate(-328.29968 185.66124) rotate(-20.26053)" fill="#2f2e41"/><polygon points="788.731 557.111 776.483 556.555 772.799 509.051 790.875 509.872 788.731 557.111" fill="#a0616a"/><path d="M767.55224,552.84974h23.64387a0,0,0,0,1,0,0v14.88687a0,0,0,0,1,0,0H752.66539a0,0,0,0,1,0,0v0A14.88686,14.88686,0,0,1,767.55224,552.84974Z" fill="#2f2e41"/><path d="M869.58878,529.29415v0a64.582,64.582,0,0,0-15.886,39.06045l-4.04838,75.29989,35.672,62.95065,19.93437-10.49177-24.13108-47.213,14.68848-45.11464L935.687,572.31043l-3.14753-45.11464Z" transform="translate(-135.62092 -166.13169)" fill="#2f2e41"/><polygon points="789.574 401.982 800.066 406.179 791.673 550.965 767.542 547.818 760.197 437.654 789.574 401.982" fill="#2f2e41"/><path d="M902.86853,553.89864A10.05579,10.05579,0,0,1,916.249,546.2358l22.67947-27.61559,5.49195,17.739-22.37878,23.50466a10.11027,10.11027,0,0,1-19.17309-5.96523Z" transform="translate(-135.62092 -166.13169)" fill="#a0616a"/><circle cx="775.61928" cy="216.38257" r="24.56103" fill="#a0616a"/><path d="M947.75252,429.09769s-28.3278-26.754-67.14737-6.29507c0,0-9.96719,101.24564-16.26225,111.73742,0,0,54.55723-7.34425,69.24572-3.14754,0,0,0-44.06545,9.4426-60.8523S947.75252,429.09769,947.75252,429.09769Z" transform="translate(-135.62092 -166.13169)" fill="#ccc"/><path d="M936.73615,431.72063l11.512-2.12727a29.45574,29.45574,0,0,1,12.61905,14.7174c4.19671,10.49178,15.73766,61.90148,15.73766,61.90148l-45.11464,45.11464-9.4426-13.63931,28.3278-37.77039L935.687,468.44185Z" transform="translate(-135.62092 -166.13169)" fill="#ccc"/><path d="M932.24851,395.65565a2.13479,2.13479,0,0,0-1.85636-2.81905,4.93046,4.93046,0,0,0-3.4761,1.715,13.8334,13.8334,0,0,1-3.07115,2.63711c-1.18812.59889-2.79953.51354-3.47685-.62824-.63605-1.07221-.20023-2.508.18482-3.75347a36.9067,36.9067,0,0,0,1.62991-9.77c.11092-3.70032-.41115-7.562-2.45972-10.44807-2.64387-3.72475-7.37142-5.13883-11.84544-5.0363s-8.87547,1.48362-13.30713,2.35665c-1.52992.30139-3.32826.4555-4.35153-.73025-1.08805-1.26082-.68844-3.3014-.22563-5.00376,1.20094-4.41743,2.475-8.98461,5.26525-12.55224a18.89839,18.89839,0,0,1,12.06081-6.79014,28.93848,28.93848,0,0,1,13.46236,1.52838,36.09628,36.09628,0,0,1,17.68285,12.3186A29.23592,29.23592,0,0,1,944.04269,380.28a26.66715,26.66715,0,0,1-9.88578,16.85462" transform="translate(-135.62092 -166.13169)" fill="#2f2e41"/><path d="M677.62092,588.13169h-533a9.01016,9.01016,0,0,1-9-9v-404a9.01047,9.01047,0,0,1,9-9h533a9.01016,9.01016,0,0,1,9,9v404A9.00984,9.00984,0,0,1,677.62092,588.13169Zm-533-420a7.00786,7.00786,0,0,0-7,7v404a7.00817,7.00817,0,0,0,7,7h533a7.00849,7.00849,0,0,0,7-7v-404a7.00818,7.00818,0,0,0-7-7Z" transform="translate(-135.62092 -166.13169)" fill="#e6e6e6"/><path d="M625.62092,262.13169h-429a9.01047,9.01047,0,0,1-9-9v-31a9.01047,9.01047,0,0,1,9-9h429a9.01016,9.01016,0,0,1,9,9v31A9.01015,9.01015,0,0,1,625.62092,262.13169Zm-429-47a7.00786,7.00786,0,0,0-7,7v31a7.00785,7.00785,0,0,0,7,7h429a7.00818,7.00818,0,0,0,7-7v-31a7.00818,7.00818,0,0,0-7-7Z" transform="translate(-135.62092 -166.13169)" fill="#3f3d56"/><path d="M366.62092,345.13169h-170a9.01047,9.01047,0,0,1-9-9v-31a9.01047,9.01047,0,0,1,9-9h170a9.01047,9.01047,0,0,1,9,9v31A9.01047,9.01047,0,0,1,366.62092,345.13169Zm-170-47a7.00786,7.00786,0,0,0-7,7v31a7.00785,7.00785,0,0,0,7,7h170a7.00786,7.00786,0,0,0,7-7v-31a7.00787,7.00787,0,0,0-7-7Z" transform="translate(-135.62092 -166.13169)" fill="#3f3d56"/><path d="M625.62092,345.13169h-170a9.01047,9.01047,0,0,1-9-9v-31a9.01047,9.01047,0,0,1,9-9h170a9.01016,9.01016,0,0,1,9,9v31A9.01015,9.01015,0,0,1,625.62092,345.13169Zm-170-47a7.00786,7.00786,0,0,0-7,7v31a7.00785,7.00785,0,0,0,7,7h170a7.00818,7.00818,0,0,0,7-7v-31a7.00818,7.00818,0,0,0-7-7Z" transform="translate(-135.62092 -166.13169)" fill="#3f3d56"/><path d="M626.12092,541.13169h-114a8.50981,8.50981,0,0,1-8.5-8.5v-11a8.50982,8.50982,0,0,1,8.5-8.5h114a8.51014,8.51014,0,0,1,8.5,8.5v11A8.51013,8.51013,0,0,1,626.12092,541.13169Z" transform="translate(-135.62092 -166.13169)" fill="#e6e6e6"/><path d="M215.62092,397.13169a8,8,0,1,1,8-8A8.00916,8.00916,0,0,1,215.62092,397.13169Zm0-14a6,6,0,1,0,6,6A6.00656,6.00656,0,0,0,215.62092,383.13169Z" transform="translate(-135.62092 -166.13169)" fill="#e6e6e6"/><path d="M215.62092,423.13169a8,8,0,1,1,8-8A8.00916,8.00916,0,0,1,215.62092,423.13169Zm0-14a6,6,0,1,0,6,6A6.00656,6.00656,0,0,0,215.62092,409.13169Z" transform="translate(-135.62092 -166.13169)" fill="#e6e6e6"/><path d="M215.62092,449.13169a8,8,0,1,1,8-8A8.00916,8.00916,0,0,1,215.62092,449.13169Zm0-14a6,6,0,1,0,6,6A6.00656,6.00656,0,0,0,215.62092,435.13169Z" transform="translate(-135.62092 -166.13169)" fill="#3f3d56"/><path d="M254.62092,382.63169a6.5,6.5,0,0,0,0,13h93a6.5,6.5,0,0,0,0-13Z" transform="translate(-135.62092 -166.13169)" fill="#e6e6e6"/><path d="M254.62092,408.63169a6.5,6.5,0,0,0,0,13h93a6.5,6.5,0,0,0,0-13Z" transform="translate(-135.62092 -166.13169)" fill="#e6e6e6"/><path d="M254.62092,434.63169a6.5,6.5,0,0,0,0,13h93a6.5,6.5,0,0,0,0-13Z" transform="translate(-135.62092 -166.13169)" fill="#e6e6e6"/><path d="M475.12092,397.13169a8,8,0,1,1,8-8A8.00916,8.00916,0,0,1,475.12092,397.13169Zm0-14a6,6,0,1,0,6,6A6.00656,6.00656,0,0,0,475.12092,383.13169Z" transform="translate(-135.62092 -166.13169)" fill="#e6e6e6"/><path d="M475.12092,423.13169a8,8,0,1,1,8-8A8.00916,8.00916,0,0,1,475.12092,423.13169Zm0-14a6,6,0,1,0,6,6A6.00656,6.00656,0,0,0,475.12092,409.13169Z" transform="translate(-135.62092 -166.13169)" fill="#3f3d56"/><path d="M475.12092,449.13169a8,8,0,1,1,8-8A8.00916,8.00916,0,0,1,475.12092,449.13169Zm0-14a6,6,0,1,0,6,6A6.00656,6.00656,0,0,0,475.12092,435.13169Z" transform="translate(-135.62092 -166.13169)" fill="#e6e6e6"/><path d="M475.12092,475.13169a8,8,0,1,1,8-8A8.00916,8.00916,0,0,1,475.12092,475.13169Zm0-14a6,6,0,1,0,6,6A6.00656,6.00656,0,0,0,475.12092,461.13169Z" transform="translate(-135.62092 -166.13169)" fill="#e6e6e6"/><path d="M513.12092,382.63169a6.5,6.5,0,1,0,0,13h93a6.5,6.5,0,0,0,0-13Z" transform="translate(-135.62092 -166.13169)" fill="#e6e6e6"/><path d="M513.12092,408.63169a6.5,6.5,0,1,0,0,13h93a6.5,6.5,0,0,0,0-13Z" transform="translate(-135.62092 -166.13169)" fill="#e6e6e6"/><path d="M513.12092,434.63169a6.5,6.5,0,1,0,0,13h93a6.5,6.5,0,0,0,0-13Z" transform="translate(-135.62092 -166.13169)" fill="#e6e6e6"/><path d="M513.12092,460.63169a6.5,6.5,0,1,0,0,13h93a6.5,6.5,0,0,0,0-13Z" transform="translate(-135.62092 -166.13169)" fill="#e6e6e6"/><path d="M215.74787,314.13169a6.5,6.5,0,1,0,0,13h111a6.5,6.5,0,0,0,0-13Z" transform="translate(-135.62092 -166.13169)" fill="#e6e6e6"/><path d="M215.74787,231.13169a6.5,6.5,0,1,0,0,13h111a6.5,6.5,0,0,0,0-13Z" transform="translate(-135.62092 -166.13169)" fill="#6c63ff"/><path d="M345.00373,316.45616a.5.5,0,0,0-.43311.75l4.24414,7.35107a.50016.50016,0,0,0,.86621,0l4.24414-7.35107a.5.5,0,0,0-.4331-.75Z" transform="translate(-135.62092 -166.13169)" fill="#6c63ff"/><path d="M474.74787,314.13169a6.5,6.5,0,0,0,0,13h111a6.5,6.5,0,0,0,0-13Z" transform="translate(-135.62092 -166.13169)" fill="#e6e6e6"/><path d="M604.00373,316.45616a.5.5,0,0,0-.43311.75l4.24414,7.35107a.50016.50016,0,0,0,.86621,0l4.24414-7.35107a.5.5,0,0,0-.4331-.75Z" transform="translate(-135.62092 -166.13169)" fill="#6c63ff"/><circle cx="339.5" cy="249" r="4" fill="#6c63ff"/><circle cx="80" cy="275" r="4" fill="#6c63ff"/><path d="M1063.1884,733.86831h-320.294a1.19068,1.19068,0,0,1,0-2.38137h320.294a1.19068,1.19068,0,0,1,0,2.38137Z" transform="translate(-135.62092 -166.13169)" fill="#3f3d56"/></svg>

	<h2><br><br><br><br> Title <br><br><br> Description</h2>
	
	<br><br><br><br><br><br><br><br>	
	<a href="electionCandidates.jsp">
		<input type="submit" class="loginBtn" value="Next">
	</a>
</body>
</html>