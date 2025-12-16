package org.lsposed.hijack.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.gyf.immersionbar.ImmersionBar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.lsposed.hijack.BuildConfig;
import org.lsposed.hijack.databinding.AboutPageItemCardBinding;
import org.lsposed.hijack.databinding.AboutPageItemCategoryBinding;
import org.lsposed.hijack.databinding.AboutPageItemContributorBinding;
import org.lsposed.hijack.databinding.AboutPageItemLicenseBinding;
import org.lsposed.hijack.databinding.AboutPageItemLineBinding;
import org.lsposed.hijack.databinding.ActivityAboutBinding;
import org.lsposed.hijack.R;
import org.lsposed.hijack.util.AppUtil;

public class AboutActivity extends AppCompatActivity {
    
    private ActivityAboutBinding binding;
    private List<Object> dataList = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setBar();
        binding.icon.setImageResource(R.drawable.ic_launcher);
        binding.slogan.setText(BuildConfig.APP_NAME);
        binding.version.setText(BuildConfig.VERSION_NAME + "(" + BuildConfig.VERSION_CODE + ")");
        LinearLayoutManager layoutManager = new CustomLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setItemViewCacheSize(30);
        binding.recyclerView.setItemAnimator(null);
        binding.recyclerView.setAdapter(new RecyclerViewAdapter());
        setTitle();
    }
    
    public void initData() {
        dataList.clear();
        dataList.add(new Category("About"));
        dataList.add(new Card("这是一款基于XposedApi开发的Xposed模块。\n\n请在Lsposed/LSPatch框架环境中使用。\n\n此模块仅供学习交流，请勿用于违法违规用途，且模块完全免费使用。\n\n使用指南:\n\t\t1.本模块核心以Dexkit + MMKV来查找方法和缓存搜索到的方法，并优先使用缓存的方法，避免过度使用Dexkit导致目标应用卡顿。\n\t\t2.遇到功能和描述的不符可能是还没缓存好方法就退出应用，下一次就使用缓存的方法导致没有查找到正确的方法，打开对应应用详情页清除缓存就好了。"));
        dataList.add(new Category("Developer"));
        dataList.add(new Contributor(R.drawable.ic_launcher, AppUtil.image.avatarUrl, "klaas8", "Developer & Designer", "https://github.com/klaas8"));
        dataList.add(new Category("Open Source"));
        dataList.add(new License("XposedBridge", "rovo89", "Apache Software License 2.0", "https://github.com/rovo89/XposedBridge"));
        dataList.add(new License("DexKit", "LuckyPray", "GNU general public license Version 3", "https://github.com/LuckyPray/DexKit"));
        dataList.add(new License("AndroidX Core", "Google", "Apache Software License 2.0", "https://developer.android.com/jetpack/androidx"));
        dataList.add(new License("AppCompat", "Google", "Apache Software License 2.0", "https://developer.android.com/jetpack/androidx"));
        dataList.add(new License("Material Components", "Google", "Apache Software License 2.0", "https://github.com/material-components/material-components-android"));
        dataList.add(new License("ImmersionBar", "geyifeng", "Apache Software License 2.0", "https://github.com/gyf-dev/ImmersionBar"));
        dataList.add(new License("FastScroll", "zhanghai", "Apache Software License 2.0", "https://github.com/zhanghai/AndroidFastScroll"));
        dataList.add(new License("MMKV", "Tencent", "BSD 3-Clause License", "https://github.com/Tencent/MMKV"));
        dataList.add(new License("Glide", "bumptech", "Apache Software License 2.0", "https://github.com/bumptech/glide"));
        dataList.add(new License("Gson", "Google", "Apache Software License 2.0", "https://github.com/google/gson"));
        dataList.add(new License("Commons IO", "Apache Software Foundation", "Apache Software License 2.0", "https://commons.apache.org/proper/commons-io/"));
        dataList.add(new License("ZXing", "zxing", "Apache Software License 2.0", "https://github.com/zxing/zxing"));
        dataList.add(new License("MapDB", "Jan Kotek", "Apache Software License 2.0", "https://github.com/jankotek/mapdb"));
    }
    
    public class Category {
        public String content;
        public Category(String content) {
            this.content = content;
        }
    }
    
    public class Card {
        public String content;
        public Card(String content) {
            this.content = content;
        }
    }
    
    public class Contributor {
        public int avatar;
        public String avatarUrl, name, desc, url;
        public Contributor(int avatar, String name, String desc, String url) {
            this.avatar = avatar;
            this.name = name;
            this.desc = desc;
            this.url = url;
        }
        public Contributor(int avatar, String avatarUrl, String name, String desc, String url) {
            this.avatar = avatar;
            this.avatarUrl = avatarUrl;
            this.name = name;
            this.desc = desc;
            this.url = url;
        }
    }
    
    public class License {
        public String library, name, license, url;
        public License(String library, String name, String license, String url) {
            this.library = library;
            this.name = name;
            this.license = license;
            this.url = url;
        }
    }

    public List<Object> getProcessItems() {
        initData();
        dataList.removeIf(item -> item.getClass() == Object.class);
        List<Object> result = new ArrayList<>();
        int size = dataList.size();
        for (int i = 0; i < size; i++) {
            Object current = dataList.get(i);
            result.add(current);
            if (current instanceof Contributor) {
                boolean hasMoreContributors = false;
                for (int j = i + 1; j < size; j++) {
                    if (dataList.get(j) instanceof Contributor) {
                        hasMoreContributors = true;
                        break;
                    }
                }
                if (hasMoreContributors) {
                    result.add(new Object());
                }
            } else if (current instanceof License) {
                boolean hasMoreLicenses = false;
                for (int j = i + 1; j < size; j++) {
                    if (dataList.get(j) instanceof License) {
                        hasMoreLicenses = true;
                        break;
                    }
                }
                if (hasMoreLicenses) {
                    result.add(new Object());
                }
            }
        }
        dataList.clear();
        dataList.addAll(result);
        return dataList;
    }
    
    public class CustomLayoutManager extends LinearLayoutManager {
        public CustomLayoutManager(Context context) {
            super(context);
        }
        @Override
        protected void calculateExtraLayoutSpace(RecyclerView.State state, int[] extraLayoutSpace) {
            Arrays.fill(extraLayoutSpace, 5000);
        }
    }
    
    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final List<Object> data;
        RecyclerViewAdapter() {
            this.data = getProcessItems();
        }
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Object type = data.get(viewType);
            if (type != null) {
                if (type instanceof AboutActivity.Category) {
                    AboutPageItemCategoryBinding binding = AboutPageItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                    return new Category(binding, (AboutActivity.Category) type, viewType);
                } else if (type instanceof AboutActivity.Card) {
                    AboutPageItemCardBinding binding = AboutPageItemCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                    return new Card(binding, (AboutActivity.Card) type, viewType);
                } else if (type instanceof AboutActivity.Contributor) {
                    AboutPageItemContributorBinding binding = AboutPageItemContributorBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                    return new Contributor(binding, (AboutActivity.Contributor) type, viewType);
                } else if (type instanceof AboutActivity.License) {
                    AboutPageItemLicenseBinding binding = AboutPageItemLicenseBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                    return new License(binding, (AboutActivity.License) type, viewType);
                }
            }
            return new Line(AboutPageItemLineBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {}

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
        
        class Line extends RecyclerView.ViewHolder {
            Line(@NonNull AboutPageItemLineBinding binding) {
                super(binding.getRoot());
            }
        }
        
        class Category extends RecyclerView.ViewHolder {
            TextView contentView;
            ImageButton actionView;
            Category(@NonNull AboutPageItemCategoryBinding binding, AboutActivity.Category category, int position) {
                super(binding.getRoot());
                contentView = binding.content;
                actionView = binding.actionIcon;
                contentView.setText(category.content);
                actionView.setVisibility(8);
            }
        }
        
        class Card extends RecyclerView.ViewHolder {
            TextView contentView;
            Card(@NonNull AboutPageItemCardBinding binding, AboutActivity.Card card, int position) {
                super(binding.getRoot());
                contentView = binding.content;
                contentView.setText(card.content);
            }
        }
        
        class Contributor extends RecyclerView.ViewHolder {
            ImageView avatarView;
            TextView nameView, descView;
            Contributor(@NonNull AboutPageItemContributorBinding binding, AboutActivity.Contributor contributor, int position) {
                super(binding.getRoot());
                avatarView = binding.avatar;
                nameView = binding.name;
                descView = binding.desc;
                nameView.setText(contributor.name);
                descView.setText(contributor.desc);
                binding.getRoot().setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(contributor.url))));
                Glide.with(getApplicationContext())
                    .load(contributor.avatarUrl)
                    .transform(new RoundedCorners(10))
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(contributor.avatar)
                    .error(contributor.avatar)
                    .fallback(contributor.avatar)
                    .into(avatarView);
            }
        }
        
        class License extends RecyclerView.ViewHolder {
            TextView contentView, hintView;
            License(@NonNull AboutPageItemLicenseBinding binding, AboutActivity.License license, int position) {
                super(binding.getRoot());
                contentView = binding.content;
                hintView = binding.hint;
                contentView.setText(license.library + " - " + license.name);
                hintView.setText(license.url + "\n" + license.license);
                binding.getRoot().setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(license.url))));
            }
        }
    }
    
    public void setTitle() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_exit_24dp);
        binding.toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
        for (int i = 0; i < binding.toolbar.getChildCount() ; i++) {
            View child = binding.toolbar.getChildAt(i);
            if (child instanceof ImageButton) {
                TooltipCompat.setTooltipText(child, "退出");
                break;
            }
        }
        binding.collapsingToolbar.setTitle(BuildConfig.APP_NAME);
        binding.collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT);
        binding.collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void setBar() {
        ImmersionBar.with(this)
            .keyboardEnable(true)
            .autoDarkModeEnable(true)
            .transparentStatusBar()
            .fitsSystemWindows(true)
            .statusBarColor(R.color.aboutBar)
            .navigationBarColor(R.color.aboutBar)
            .init();
    }
}
