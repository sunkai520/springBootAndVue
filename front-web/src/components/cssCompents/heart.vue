<template>
  <div class="bdy">
    <div class="heart">
        <div class="left"></div>
        <div class="right"></div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style lang="scss" scoped>
    .bdy{
        height: 200px;
        width: 200px;
        background-color: inherit;
        .heart{
            animation: beat 0.5s linear infinite alternate; //linear匀速 infinite无限循环 alternate反向动画1-1.1倍为正向 1.1-1为反向
            .left,.right{
                width: 100px;
                height: 150px;
                float: left;
                background-color: pink;
                border-radius: 50px 50px 0 0;
                box-shadow: 0 0 10px pink;
            }
            .left{
                transform: translateX(32px) rotate(-45deg);
            }
            .right{
               transform: translateX(-32px) rotate(45deg);
            }
        }
    }
    @keyframes beat{
        // 起始时的动画
        0%{
            transform: scale(1);
        }
        // 结束时的动画
        100%{
            transform: scale(1.1);
        }
    }
</style>