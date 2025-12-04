# OOP-Design-course
گزارش گام اول پروژه : 
<h2>تغییرات مربوط به افزودن روش جدید ارسال پیام:</h2>
<br>
تغییر در کلاس ReservationService :
اضافه کردن:
           SmsSender smsSender = new SmsSender();
           smsSender.sendMessage(res.customer.mobile, "Your reservation confirmed!");
           break;
           
در case sms
