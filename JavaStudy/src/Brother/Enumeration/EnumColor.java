package Brother.Enumeration;

/**枚举实现接口
 * Created by tzh on 1/21/17.
 */
public enum EnumColor implements Info {
    RED{
        @Override
        public String getColor() {
            return "红色";
        }
    },
    GREEN{
        @Override
        public String getColor() {
            return "绿色";
        }
    },
    BLUE{
        @Override
        public String getColor() {
            return "蓝色";
        }
    };


}

interface Info {
    public String getColor();
}
