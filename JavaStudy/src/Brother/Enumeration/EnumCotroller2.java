package Brother.Enumeration;

/**枚举中定义抽象方法
 * Created by tzh on 1/21/17.
 */
public enum EnumCotroller2 {
    ON {
        @Override
        public EnumCotroller2 downAction() {
            return OFF;
        }
    }, OFF {
        @Override
        public EnumCotroller2 downAction() {
            return ON;
        }
    };
    public abstract EnumCotroller2 downAction();


}
