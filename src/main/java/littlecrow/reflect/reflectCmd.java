package littlecrow.reflect;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// not used since the Age of Exile mod is just to nested to use with reflection
// I tried but honestly a fork would be much easier
public class reflectCmd {

        public reflectCmd() throws Exception {

            // test case for reflection
            Class<?> warp = Class.forName("littlecrow.WarpCrow");
            Method cmd = warp.getMethod("createItemGroup", String.class, String.class);
            cmd.invoke(warp, "warpacrow", "new_group");

            Class<?> StatRequirement = Class.forName("com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases.StatRequirement");
            Method setBaseDex = StatRequirement.getDeclaredMethod("setBaseDex", int.class);
            Method setDex = StatRequirement.getDeclaredMethod("setDex", float.class);
            Constructor<?> statReqConst = StatRequirement.getConstructor();
            Object sR = statReqConst.newInstance();
            setBaseDex.invoke(sR, 5);
            setDex.invoke(sR, 0.2F);
            System.out.println(sR.getClass().getDeclaredField("base_req").get(sR));

            Class<?> StatAttribute = Class.forName("com.robertx22.age_of_exile.database.data.perks.StatAttribute");
            Field DEX = StatAttribute.getDeclaredField("DEX");
            /*
            Object DEX = null;
            for (Object o : att) {
                System.out.println(o);
                    if (o.toString().equals("DEX")) {
                           DEX = o;
                           break;
                    }
            }*/

            //not really needed anymore since I created a little enum myself
            Class<?> SupportGemTags = Class.forName("com.robertx22.age_of_exile.database.data.skill_gem.SupportGemTags");
            List<Class<?>> s = Arrays.asList(SupportGemTags);
            List<SupportGemTags> listS = Arrays.asList(littlecrow.reflect.SupportGemTags.projectile);

            Class<?> StatMod = Class.forName("com.robertx22.age_of_exile.database.data.StatModifier");
            Class<?> Stat = Class.forName("com.robertx22.age_of_exile.aoe_data.database.stats.Stats");
            Class<?> Stat2 = Class.forName("com.robertx22.age_of_exile.database.data.stats.Stat");


            Field as = Stat.getDeclaredField("ATTACK_SPEED");
            as.setAccessible(true);
            Object fieldValue = as.get(Stat);
            Method getMethod = fieldValue.getClass().getDeclaredMethod("get");
            System.out.println(as.get(Stat));
            Constructor<?>[] StatModC = StatMod.getConstructors();
            Object StatModifier =  StatModC[1].newInstance(1, 1, getMethod.invoke(fieldValue));

            Class<?> intGems = Class.forName("com.robertx22.age_of_exile.aoe_data.database.skill_gems.SkillGemBuilder");
            Method[] m = intGems.getDeclaredMethods();
            System.out.println(Arrays.toString(m));

            /*
            Method registerAll = intGems
                    .getDeclaredMethod("of",
                            String.class, String.class,
                            StatRequirement, StatAttribute,
                            float.class, List.class, StatMod);Arrays.asList(SupportGemTags.getDeclaredField("projectile"))
                            para[0].getClass(), para[1], para[2], para[3], para[4], para[5], para[6], para[7]
                            */
            m[0].invoke(
                    intGems,
                    "reflect_try_dex",
                    "Reflect Try Dex",
                    sR, DEX,
                    1.5F,
                    s,
                    StatModifier);
        }
}

enum SupportGemTags {
    attack,
    spell,
    heal,
    aggro,
    damage,
    projectile;
}

enum StatAttribute {
    INT("int"), DEX("dex"), STR("str");

    public String id;

    StatAttribute(String id) {
        this.id = id;
    }
}