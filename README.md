# butter knife 使用

* [添加依赖](#添加依赖)
* [ButterKnife使用](#butterknife使用)
    * [View绑定](#view绑定)
    * [资源绑定](#资源绑定)
    * [在非activity中绑定](#在非activity中绑定)
    * [View列表绑定](#view列表绑定)
    * [监听器绑定](#监听器绑定)
    * [重复绑定](#重复绑定)
    * [可选绑定](#可选绑定)
    

## 添加依赖

1. Project 的 build.gradle 添加：
```
dependencies {
  classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
}
```

2. App 的 build.gradle 添加：
```
apply plugin: 'com.neenbedankt.android-apt'

dependencies {
  compile 'com.jakewharton:butterknife:8.0.1'
  apt 'com.jakewharton:butterknife-compiler:8.0.1'
}
```

studio 里面还有一个ButterKnife 注解框架的偷懒插件名为“Android Butterknife Zelezny”


## ButterKnife使用

#### View绑定

View的绑定注解,BindView标签，并且调用ButterKnife.bind(this)

```
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_content)
    TextView tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
```

#### 资源绑定

绑定资源到类成员上可以使用@BindBool、@BindColor、@BindDimen、@BindDrawable、@BindInt、@BindString。

```
@BindString(R.string.app_name)
String appName;
@BindColor(R.color.black)
int black;
```

#### 在非activity中绑定

在Fragment中
```
public class FancyFragment extends Fragment {
    @BindView(R.id.button1) Button button1;
    @BindView(R.id.button2) Button button2;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fancy_fragment, container, false);
        ButterKnife.bind(this, view);
        // TODO Use fields...
        return view;
    }
}
```

在adapter中
```
public class MyAdapter extends BaseAdapter {
    @Override public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.whatever, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.name.setText("John Doe");
        // etc...

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.title)
        TextView name;
        @BindView(R.id.job_title) TextView jobTitle;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
```

#### View列表绑定

可以一次性将多个views绑定到一个List或数组中：
```
@BindViews({ R.id.first_name, R.id.middle_name, R.id.last_name })
List<EditText> nameViews;
```

#### 监听器绑定

监听器能够自动的绑定到特定的执行方法上：
```
@OnClick(R.id.submit)
public void submit(View view) {
  // TODO submit data to server...
}
```

监听方法可以没有参数
```
@OnClick(R.id.submit)
public void submit() {
  // TODO submit data to server...
}
```

添加方法可以指定一个类型
```
@OnClick(R.id.submit)
public void sayHi(Button button) {
    button.setText("Hello!");
}
```

#### 重复绑定

Fragment的生命周期与Activity不同。  
在Fragment中，如果你在onCreateView中使用绑定，
那么你需要在onDestroyView中设置所有view为null。  
为此，ButterKnife返回一个Unbinder实例以便于你进行这项处理。  
在合适的生命周期回调中调用unbind函数就可完成重置。
```
public class FancyFragment extends Fragment {
    @BindView(R.id.button1) Button button1;
    @BindView(R.id.button2) Button button2;
    private Unbinder unbinder;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fancy_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        // TODO Use fields...
        return view;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
```

#### 可选绑定

@bind和监听器都是必须的，如果目标没有找到，ButterKnife会抛出异常，  
所以如果想创建一个可选绑定是，前面需要加@Nullable或者@Optional
```
@Nullable @BindView(R.id.tv_content2) TextView tvContent2;
@Optional @OnClick(R.id.tv_content3)
void clickContents(){
    // TODO
}
```



