<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>

<%@ include file="/WEB-INF/views/include/head.jsp"%>
    <title>캘린더</title>
  
	<link type="text/css" rel="stylesheet" href="/resources/css/nav.css?ver=4">
     <link type="text/css" rel="stylesheet" href="https://bootswatch.com/5/flatly/bootstrap.min.css">
    <script src="https://bootswatch.com/_vendor/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <script src='/resources/js/calendar/main.js'></script>
    <link href='/resources/css/calendar/main.css' rel='stylesheet' />

<style type="text/css">
        html,
        body {

            width: 100%;
            height: 100%;
            min-width: 1000px;

        }

        .wrap {
            height: 100%;
            display: flex;
            flex-direction: column;
            align-items: stretch;
            flex: 1 auto;


        }

        header {
            height: 60px;
            background: rgb(143, 122, 229);
            background: radial-gradient(circle, rgba(143, 122, 229, 1) 0%, rgba(241, 112, 170, 1) 100%);
            min-height: 60px;

        }

        .con {

            height: 100%;
            display: flex;
            justify-content: space-between;
            overflow-x: auto;

        }

        nav {
            background: rgb(19, 23, 34);
            background: linear-gradient(0deg, rgba(19, 23, 34, 1) 0%, rgba(26, 17, 47, 1) 22%, rgba(26, 30, 41, 1) 83%, rgba(19, 23, 34, 1) 100%);


            width: 220px;
            height: 100%;
            flex-shrink: 0;
            overflow: auto;



        }

        section {

            flex: 1;
            width: 100%;
            background: RGB(245, 246, 247);
            display: flex;
            justify-content: center;
            overflow-y: auto;
            min-width: 1300px;
            overflow-x: hidden;




        }


        aside {

            background: rgb(19, 23, 34);
            background: linear-gradient(0deg, rgba(19, 23, 34, 1) 0%, rgba(26, 17, 47, 1) 22%, rgba(26, 30, 41, 1) 83%, rgba(19, 23, 34, 1) 100%);

            height: 100%;
            width: 200px;
            flex-shrink: 0;

            overflow: auto;


        }


        /*섹션 영역////////////////////////////////////////////////////////////*/

        .section-wrap {
            margin: 0 50px;
            border: solid red(128, 123, 123);
            height: auto;
            width: 90%;
            display: flex;
            flex-direction: column;
            align-items: center;

        }

        .cat-subject {
            font-size: 25px;
            margin: 20px 0px;
            font-weight: 900;
            width: 100%;

        }

        .content-wrap {

            width: auto;
            display: flex;
            flex-direction: column;
            align-items: center;


        }

        #calendar {

            width:1050px;

        }
        
        a {
        text-decoration-line : none;
        text-decoration : none;
        }
        
        
        .fc-daygrid-day-frame:hover {
            background-color: RGB(187, 168, 223)

        }
        

    </style>
</head>

<body>


    <div class="wrap">
        <header>


			<%@ include file="/WEB-INF/views/include/nav/header.jsp" %>


        </header>

        <div class="con">
            <nav>
     <%@ include file="/WEB-INF/views/include/nav/main-nav.jsp" %>
   

            </nav>
            <section>
                <div class="section-wrap">


                    <div class="cat-subject">
                        #캘린더
                        <hr>
                    </div>
                    <div class="content-wrap">

                        <div id='calendar'></div>

                    </div>
                </div>




            </section>
            <aside></aside>
        </div>

    </div>






    <script>

        let today = new Date();

        document.addEventListener('DOMContentLoaded', function () {
            var calendarEl = document.getElementById('calendar');

            var calendar = new FullCalendar.Calendar(calendarEl, {
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth'
                },
                initialDate: today,
                selectable: true,
                selectMirror: true,
                select: function (arg) {
                    alert('이벤트');
                    var title = prompt('Event Title:');
                    if (title) {
                        calendar.addEvent({
                            title: title,
                            start: arg.start,
                            end: arg.end,
                            allDay: arg.allDay
                        })
                    }
                    calendar.unselect()
                },
                eventClick: function (arg) {
                    alert("클릭시 발생하는 이벤트");
                },
                eventChange: function (arg) {
                    alert("체인지시 발생하는 이벤트");
                },
                editable: true,
                dayMaxEvents: true, // allow "more" link when too many events
                events: [
                    {
                        id: 'aa',
                        title: 'All Day Event',
                        start: '2021-11-01',
                        backgroundColor: 'red',
                        borderColor: 'red'
                    },
                    {
                        title: 'Long Event',
                        start: '2021-11-07',
                        end: '2021-11-10'
                    },
                    {
                        groupId: 999,
                        title: 'Repeating Event',
                        start: '2020-09-09T16:00:00'
                    },
                    {
                        groupId: 999,
                        title: 'Repeating Event',
                        start: '2020-09-16T16:00:00'
                    },
                    {
                        title: 'Conference',
                        start: '2020-09-11',
                        end: '2020-09-13'
                    },
                    {
                        title: 'Meeting',
                        start: '2020-09-12T10:30:00',
                        end: '2020-09-12T12:30:00'
                    },
                    {
                        title: 'Lunch',
                        start: '2020-09-12T12:00:00'
                    },
                    {
                        title: 'Meeting',
                        start: '2020-09-12T14:30:00'
                    },
                    {
                        title: 'Happy Hour',
                        start: '2020-09-12T17:30:00'
                    },
                    {
                        title: 'Dinner',
                        start: '2020-09-12T20:00:00'
                    },
                    {
                        title: 'Birthday Party',
                        start: '2020-09-13T07:00:00'
                    },
                    {
                        title: 'Click for Google',
                        url: 'http://google.com/',
                        start: '2020-09-28'
                    }
                ]
            });

            calendar.render();


            let aaa = calendar.getEventById('aa');
            aaa.start = '2021-11-10';

            calendar.addEvent({
                title: 'All Day Event',
                start: '2021-11-03',
                backgroundColor: 'red',
                borderColor: 'red'
            });

        });
        



    </script>
</body>


</html>