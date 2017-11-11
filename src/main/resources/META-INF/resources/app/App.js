Ext.define('Music.App', {
    extend: 'Ext.container.Viewport',
    layout: 'border',

    tabTitleHeight: 28,     // tab标签高度
    menuWidth: 250,         // 系统菜单宽度
    logoHeight: 60,         // 左上角LOGO高度

    initComponent: function () {
        var me = this;
        me.mainTab = Ext.create('Ext.tab.Panel', {
            id: 'mainTab',
            region: 'center',
            cls: 'main-tab',
            margin: -me.tabTitleHeight + ' 0 0 0',
            items: [{
                title: '网易云音乐',
                layout: 'fit',
                items: [Ext.create('Music.MusicGridPanel', {
                    source: '163'
                })]
            }, {
                title: '酷我音乐',
                layout: 'fit',
                items: [Ext.create('Music.MusicGridPanel', {
                    source: 'kuwo'
                })]
            }]
        });
        Ext.apply(this, {
            items: [{
                region: 'north',
                height: me.logoHeight,
                cls: 'app-header',
                layout: 'border',
                items: [{
                    region: 'west',
                    layout: 'fit',
                    width: me.menuWidth + 1,
                    html: '<div class="logo"><h1></h1></div>'
                }, {
                    region: 'center'
                }]
            }, {
                region: 'west',
                layout: 'fit',
                width: me.menuWidth,
                style: 'border-right: 1px solid silver;'
            }, me.mainTab, {
                region: 'south',
                height: 100,
                style: 'border-top: 1px solid silver;',
                html: '<audio id="audio"></audio>'
            }],
            listeners: {
                afterrender: function () {
                    me.audio = document.getElementById('audio');
                    me.audio.addEventListener('ended', function () {
                        me.audio.play();
                    }, false);
                }
            }
        });
        me.callParent(arguments);
    }
});