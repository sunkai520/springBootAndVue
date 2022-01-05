<template>
  <div class="start">
    <div class="tstar" v-for="(k, ind) in 30" :key="ind"></div>
    <div class="night">
      <div class="shooting_star" v-for="(k, index) in 15" :key="index"></div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.start {
  width: 100%;
  height: 100%;
  background-image: linear-gradient(black, #3d67a7);
}
.night {
  position: relative;
  width: 100%;
  height: 100%;
  transform: rotateZ(35deg);
}

.shooting_star {
  position: absolute;
  left: 20%;
  top: 50%;
  height: 2px;
  background: linear-gradient(-45deg, #5f91ff, rgba(0, 0, 255, 0));
  border-radius: 999px;
  filter: drop-shadow(0 0 6px #699bff);
  -webkit-animation: tail 3000ms ease-in-out infinite,
    shooting 3000ms ease-in-out infinite;
  animation: tail 3000ms ease-in-out infinite,
    shooting 3000ms ease-in-out infinite;
}
.shooting_star::before {
  content: "";
  position: absolute;
  top: calc(50% - 1px);
  right: 0;
  height: 2px;
  background: linear-gradient(
    -45deg,
    rgba(0, 0, 255, 0),
    #5f91ff,
    rgba(0, 0, 255, 0)
  );
  transform: translateX(50%) rotateZ(45deg);
  border-radius: 100%;
  -webkit-animation: shining 3000ms ease-in-out infinite;
  animation: shining 3000ms ease-in-out infinite;
}
.shooting_star::after {
  content: "";
  position: absolute;
  top: calc(50% - 1px);
  right: 0;
  height: 2px;
  background: linear-gradient(
    -45deg,
    rgba(0, 0, 255, 0),
    #5f91ff,
    rgba(0, 0, 255, 0)
  );
  transform: translateX(50%) rotateZ(45deg);
  border-radius: 100%;
  -webkit-animation: shining 3000ms ease-in-out infinite;
  animation: shining 3000ms ease-in-out infinite;
  transform: translateX(50%) rotateZ(-45deg);
}
.tstar {
  position: absolute;
  left: 10px;
  top: 11px;
  height: 5px;
  width: 5px;
  background-color: rgb(164, 175, 115);
  border-radius: 20px;
  box-shadow: 0px 1px 5px 5px rgb(164, 175, 115);
}
@for $i from 1 through 30 {
  $rd: random(5000);
  $d: random(
    $limit: 5,
  );
  @if $i < 15 {
    .shooting_star:nth-child(#{$i}) {
      top: calc(60% - random(500) * 1px);
      left: calc(20% - random(200) * 1px);
      -webkit-animation-delay: $rd * 1ms;
      animation-delay: $rd * 1ms;
    }
    .shooting_star:nth-child(#{$i})::before,
    .shooting_star:nth-child(#{$i})::after {
      -webkit-animation-delay: $rd * 1ms;
      animation-delay: $rd * 1ms;
    }
  }
  .tstar:nth-child(#{$i}) {
    left: random($limit: 1920) * 1px;
    top: random($limit: 800) * 1px;
    height: $d * 1px;
    width: $d * 1px;
    box-shadow: 0px 1px $d * 1px $d * 1px rgb(164, 175, 115);
  }
}

@-webkit-keyframes tail {
  0% {
    width: 0;
  }
  30% {
    width: 100px;
  }
  100% {
    width: 0;
  }
}

@keyframes tail {
  0% {
    width: 0;
  }
  30% {
    width: 100px;
  }
  100% {
    width: 0;
  }
}
@-webkit-keyframes shining {
  0% {
    width: 0;
  }
  50% {
    width: 30px;
  }
  100% {
    width: 0;
  }
}
@keyframes shining {
  0% {
    width: 0;
  }
  50% {
    width: 30px;
  }
  100% {
    width: 0;
  }
}
@-webkit-keyframes shooting {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(1000px);
  }
}
@keyframes shooting {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(1000px);
  }
}
@-webkit-keyframes sky {
  0% {
    transform: rotate(45deg);
  }
  100% {
    transform: rotate(405deg);
  }
}
@keyframes sky {
  0% {
    transform: rotate(45deg);
  }
  100% {
    transform: rotate(405deg);
  }
}
</style>