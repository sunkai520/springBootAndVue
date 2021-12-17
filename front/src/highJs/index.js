import Hljs from 'highlight.js';
import 'highlight.js/styles/tomorrow-night-bright.css'; // 代码高亮风格，选择更多风格需导入 node_modules/hightlight.js/styles/ 目录下其它css文件

let Highlight = {};
// 自定义插件
Highlight.install = function (Vue) {
    // 自定义指令 v-highlight
    Vue.directive('highlight', {
        mounted: function(el) {
            console.log(el);
            let blocks = el.querySelectorAll('pre code');
            console.log(blocks,"blocks")
            for (let i = 0; i < blocks.length; i++) {
                Hljs.highlightBlock(blocks[i]);
            }
        },

    })
};

export default Highlight;