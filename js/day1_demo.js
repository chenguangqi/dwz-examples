$(function() {
    console.log("开始初始化DWZ, Version:" + DWZ.version)

    DWZ.init("/dwz_jui-master/dwz.frag.xml", {
        debug: true,
        callback: function() {
            initEnv();
            $("#themeList").theme({themeBase:"dwz_jui-master/themes"});
            console.log("DWZ初始化完成。");
        }
    })
});