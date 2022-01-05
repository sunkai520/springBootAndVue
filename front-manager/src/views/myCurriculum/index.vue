<template>
  <div class="myCurriculum">
    <table class="table" border="1" align="center" cellpadding="10">
      <tr style="height: 60px">
        <th colspan="7">我的个人简历</th>
      </tr>
      <tr>
        <th>姓名:</th>
        <td>孙凯</td>
        <th>年龄:</th>
        <td>26</td>
        <th>民族</th>
        <td>汉</td>
        <td rowspan="3">ddd</td>
      </tr>
      <tr>
        <th>出生日期:</th>
        <td>1994</td>
        <th>政治面貌</th>
        <td>团员</td>
        <th>学历</th>
        <td>本科</td>
      </tr>
      <tr>
        <th>专业:</th>
        <td>物联网</td>
        <th>毕业学校:</th>
        <td>山东交通</td>
        <th>邮编</th>
        <td>27545</td>
      </tr>
      <tr>
        <th>爱好:</th>
        <td>玩，拆，装</td>
        <th>籍贯</th>
        <td>鲁</td>
        <th>邮箱</th>
        <td colspan="2">861602355@qq.com</td>
      </tr>
      <tr>
        <th>自我评价:</th>
        <td colspan="6">
          <p>
            1. 态度务实端庄，性格开朗，具有抗压性和坚持力。<br>
            2. 遇到问题会通过各种方法解决，并且有解决问题的经验。<br>
            3. 凭借学过的知识，对于新技术也能很快上手。<br>
            4. 有良好的代码习惯，能编写出高效、高性能、具有高可维护性的代码。 热爱前端，好学，喜欢在前端领域折腾。 逻辑分析能力强，善于沟通，较强的学习能力，具备良好的沟通能力和团队协作精神。
          </p>
        </td>
      </tr>
      <tr>
        <th>掌握技术:</th>
        <td colspan="6">
          <p>
            1.熟练使用html5,css3,javascript,ES6,typescript,node,nwjs<br>
            2.熟练使用vue2,vue3,vue全家桶<br>
            3.熟练微信小程序开发<br>
            4.掌握webpack项目构建<br>
            5.掌握java基础语法<br>
            6.掌握javas pringboot微服务,SSM框架<br>
            7.了解flutter开发<br>
            8.了解jest自动化测试技术<br>
            9.了解angular8
          </p>
        </td>
      </tr>
      <tr>
        <th>工作经历:</th>
        <td colspan="6">
          <p>
            1.做事塌实认真,能吃苦耐劳;学习潜力强;2.能够很快的理解新事物;性格开朗,诚实正直,谦和自信,乐于进取乐于奉献,勤奋好学,用心上进,有较强的社会适应潜力。工作踏实,认真细致,具有强烈的事业心和工作职责心,善于处理人际关系,具...
          </p>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.myCurriculum {
  height: 100%;
  width: 100%;
  padding: 20px;
  overflow: auto;
  .table {
    width: 1000px;
    height: 100%;
    th {
      background: -webkit-linear-gradient(
        to right,
        burlywood,
        rgb(221, 161, 82),
        rgb(245, 141, 6)
      );
      background: linear-gradient(
        to right,
        burlywood,
        rgb(221, 161, 82),
        rgb(245, 141, 6)
      );
      background: -moz-linear-gradient(
        to right,
        burlywood,
        rgb(221, 161, 82),
        rgb(245, 141, 6)
      );
      background: -o-linear-gradient(
        to right,
        burlywood,
        rgb(221, 161, 82),
        rgb(245, 141, 6)
      );
      background: -ms-linear-gradient(
        to right,
        burlywood,
        rgb(221, 161, 82),
        rgb(245, 141, 6)
      );
      color: black;
      width: 100px;
    }
    td {
      color: white;
    }
    th,
    td {
      border: 1px solid burlywood;
    }
    tr:nth-child(n + 1):nth-child(-n + 5) {
      height: 40px;
    }
  }
  background: url("../../assets/img/myCurriculumBack.jpg");
}
</style>