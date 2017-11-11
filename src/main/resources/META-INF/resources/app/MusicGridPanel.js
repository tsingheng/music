Ext.define('Music.MusicGridPanel', {

    extend: 'Ext.grid.Panel',

    source: '',

    columns: [{
        text: '歌名',
        flex: 4,
        dataIndex: 'name'
    }, {
        text: '演唱',
        flex: 1,
        dataIndex: 'author'
    }, {
        text: '专辑',
        flex: 2,
        dataIndex: 'album'
    }],

    initComponent: function(){
        var me = this;
        me.menu = Ext.create('Ext.menu.Menu', {
            items: [{
                text: '试听',
                handler: function () {
                    app.audio.src = me.getSelection()[0].get('url');
                    app.audio.play();
                }
            }, {
                text: '下载',
                handler: function () {
                    var song = me.getSelection()[0];
                    window.location.href = '/' + me.source + '/download?id=' + song.get('id') + '&name=' + song.get('name') + "-" + song.get('author');
                }
            }]
        });
        me.input = Ext.create('Ext.form.field.Text');
        Ext.apply(me, {
            store: Ext.create('Ext.data.JsonStore', {
                autoLoad: false,
                proxy: {
                    type: 'ajax',
                    actionMethods: {
                        create : 'POST',
                        read   : 'POST', // by default GET
                        update : 'POST',
                        destroy: 'POST'
                    },
                    url: '/' + me.source + '/search',
                    extraParams: {
                        keywords: ''
                    },
                    reader: {
                        type: 'json',
                        root: 'data'
                    }
                }
            }),
            tbar: [me.input, {
                text: '搜索',
                handler: me.search,
                scope: me
            }],
            bbar: Ext.create('Ext.toolbar.Paging', {
                store: me.store
            }),
            listeners: {
                itemcontextmenu: function (grid, record, item, index, e) {
                    e.preventDefault();
                    me.menu.showAt(e.getXY());
                }
            }
        });
        me.callParent(arguments);
    },

    search: function () {
        var me = this;
        var keywords = me.input.getValue();
        me.getStore().getProxy().setExtraParam('keywords', keywords);
        me.getStore().reload();
    }
});