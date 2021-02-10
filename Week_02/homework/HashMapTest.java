package Week_02.homework;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * HashMap API Test
 *
 * @author jixiaobo
 */
public class HashMapTest {
  public static void main(String[] args){
    HashMap<String,String> hashMap = new HashMap<>(16);

    // 存入值(>>>>:{1=A, 2=B, 3=C})
    hashMap.put("1","A");
    hashMap.put("2","B");
    hashMap.put("3","C");
    // putIfAbsent 当key不存在时添加, key存在时不做任何操作
    hashMap.putIfAbsent("1","A-putIfAbsent");
    System.out.println(hashMap);

    // 返回元素个数(>>>>:3)
    int size = hashMap.size();
    System.out.println(size);

    // 获取值(>>>>:A)
    String value = hashMap.get("1");
    System.out.println(value);
    // 获取key值对应的value，当key不存在的时候，返回默认值(>>>>:default)
    String def = hashMap.getOrDefault("5", "default");
    System.out.println(def);

    // 是否包含key/value值,是否为空(>>>>:true false false)
    boolean bkey = hashMap.containsKey("1");
    boolean bvalue = hashMap.containsKey("A");
    boolean isempty = hashMap.isEmpty();
    System.out.println(bkey + " " + bvalue + " " + isempty);

    // putAll，讲一个具体的MAP中的映射关系全部复制到另一个map中，hashmap如果有相同的key，将会被覆盖(>>>>:{1=T1, 2=B, 3=C, 4=T})
    HashMap<String,String> temp = new HashMap<>(2);
    temp.put("4","T");
    temp.put("1","T1");
    hashMap.putAll(temp);
    System.out.println(hashMap);

    // remove-移除key值或者key(>>>>:{1=T1, 3=C, 4=T})
    hashMap.remove("2");
    // remove-只有完全匹配才会被移除，下面的key=3 value=A不存在所有不被移除1
    hashMap.remove("3","A");
    System.out.println(hashMap);

    // keySet-获取hashmap中的所有key(>>>>:1 3 4  )
    Set<String> keys = hashMap.keySet();
    for (String str :  keys) {
        System.out.print(str + " ");
    }
    System.out.println();

    // keySet-获取hashmap中的所有值(>>>>: T1 C T  )
    Collection<String> values = hashMap.values();
    for (String str :  values) {
      System.out.print(str + " ");
    }
    System.out.println();

    // replace-替换value的逻辑，(>>>>: {1=A-replace, 3=A-replaceAll, 4=A-replaceAll})
    hashMap.replace("1", "A+");
    // replaceAll-替换所有的值为100
    hashMap.replaceAll((k,v) -> v = "A-replaceAll");
    // replace-匹配上key+value的值之后才会被替换
    hashMap.replace("1","A-replaceAll","A-replace");
    System.out.println(hashMap);

    // entrySet-获取所有值(>>>>: 1=A-replace 3=A-replaceAll 4=A-replaceAll )
    Set<Entry<String, String>> mapEvtry = hashMap.entrySet();
    for (Entry<String,String> entry : mapEvtry ) {
      System.out.print(entry + " ");
    }
    System.out.println();

    // forEach-lambda表达式，更简单的for循环(>>>>: 1 A-replace3 A-replaceAll4 A-replaceAll)
    hashMap.forEach(
        (k, v) -> {
          System.out.print(k + " " + v);
        });
    System.out.println();

    // merge-合并value的旧值和新值(>>>>: {1=A-replacenew, 3=A-replaceAll, 4=A-replaceAll})
    hashMap.merge("1", "new", (oldValue, newValue) -> oldValue + newValue);
    System.out.println(hashMap);

    // 对不存在的key进行计算,如果是数值型 可以进行相应的数值计算(>>>>: {1=A-replacenew, 2=computeIfAbsent, 3=A-replaceAll, 4=A-replaceAll})
    hashMap.computeIfAbsent("2", (key) -> "computeIfAbsent");
    System.out.println(hashMap);
    // 对存在的key进行计算,如果是数值型 可以进行相应的数值计算(>>>>:{1=A-replacenew computeIfAbsent, 2=computeIfAbsent, 3=A-replaceAll, 4=A-replaceAll} )
    hashMap.computeIfPresent("1", (key, oldvalue) -> oldvalue + " computeIfAbsent" );
    System.out.println(hashMap);
    // 对指定key进行计算,不存在的时候添加，存在的时候修改(>>>>: {1=A-replacenew computeIfAbsent, 2=computeIfAbsent, 3=A-replaceAll, 4=A-replaceAll, 6=null computeIfAbsent})
    hashMap.compute("6", (key, oldvalue) -> oldvalue + " computeIfAbsent" );
    System.out.println(hashMap);

    // clone，由当前的数据浅拷贝，key和value还是一份内存地址，不会被拷贝(>>>>: {1=A-replacenew computeIfAbsent, 2=computeIfAbsent, 3=A-replaceAll, 4=A-replaceAll, 6=null computeIfAbsent})
    Object cloneMap = hashMap.clone();
    hashMap.put("1", "clone");
    hashMap.clear();
    System.out.println(cloneMap);

  }

}
