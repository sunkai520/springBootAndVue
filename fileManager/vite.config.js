import {
  defineConfig,
  loadEnv
} from 'vite'
import vue from '@vitejs/plugin-vue'
import { join } from 'path';
function resolve(dir){
  return join(__dirname,dir)
}
function wrapperEnv(envConf){
  const ret = {};

  for (const envName of Object.keys(envConf)) {
    let realName = envConf[envName].replace(/\\n/g, '\n');
    realName = realName === 'true' ? true : realName === 'false' ? false : realName;

    if (envName === 'VITE_PORT') {
      realName = Number(realName);
    }
    if (envName === 'VITE_PROXY' && realName) {
      try {
        realName = JSON.parse(realName.replace(/'/g, '"'));
      } catch (error) {
        realName = '';
      }
    }
    ret[envName] = realName;
    if (typeof realName === 'string') {
      process.env[envName] = realName;
    } else if (typeof realName === 'object') {
      process.env[envName] = JSON.stringify(realName);
    }
  }
  return ret;
}
// https://vitejs.dev/config/
export default (({
  command,
  mode
}) => {
  console.log(process.cwd(),"cwd")
  const ed = loadEnv(mode, process.cwd());
  const root = process.cwd();
  const viteEnv = wrapperEnv(ed);
  // console.log(viteEnv,"viteEnv")
  const { VITE_PORT, VITE_PUBLIC_PATH, VITE_PROXY, VITE_DROP_CONSOLE } = viteEnv;
  // console.log(ed.VITE_API_URL, "当前环境变量")
  return {
    base: VITE_PUBLIC_PATH,
    root,
    plugins: [vue()],
    server: {
      proxy: {
        '/api': {
          target: ed.VITE_API_URL,
          changeOrigin: true,
          rewrite: path => path.replace(/^\/api/, '')
        }
      },
      
    },
    
    define: {

    },
    resolve:{
      alias:{
        '@':resolve('src')
      }
    },
    optimizeDeps:{
      include:[
      ]
    }
  }
})