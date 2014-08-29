window.Popup = {
    options: null,

    window: function () {
        return Popup.$window ? Popup.$window : Popup.$window = $('#popup');
    },

    overlay: function () {
        return Popup.$overlay ? Popup.$overlay : Popup.$overlay = $('#popup-overlay');
    },

    keydown: function(e) {
        var key = e.which || e.keyCode || e.charCode;
        if(key === 27) {
            Popup.close();
        }
    },

    show: function (options) {
        options.width = options.width || 350;

        Popup.overlay().show().bind('keydown', Popup.keydown);
        Popup.resizeOverlay();
        $(document).bind('keydown', Popup.keydown).focus();

        if (!options.modal) {
            Popup.overlay().bind('click', Popup.close);
        } else {
            Popup.overlay().unbind('click', Popup.close);
        }

        Popup.window().html('');

        var loading = false;

        if (options.url) {
            loading = true;
            Popup.window().addClass('loading');

            $.ajax({
                type: 'POST',
                url: options.url,
                data: options.data,
                success: function (data, textStatus) {
                    Popup.window().removeClass('loading');
                    Popup.window().html(data);
                },
                error: function (data, textStatus) {
                    Popup.window().load('/referrals/invite', options.data, function () { Popup.window().removeClass('loading'); });
                }
            });

        } else if(options.html) {
            if (options.html.jQuery) {
                Popup.window().append(options.html);
            } else {
                Popup.window().html(options.html);
            }
        }

        if (options.cssClass) {
            Popup.window().removeClass().addClass(loading ? 'loading' : '').addClass(options.cssClass + '');
        }

        Popup.window().show().css({ width: options.width + 'px' });

        // Center the popup vertically - It has a height of 622px
        t = ($(window).height()/2) - (622/2);
        if (t < 5) {
            t = 30;
        }

        options.top = options.top ? options.top : t;
        Popup.options = options;

        Popup.updateLayout();

        return false;
    },

    confirm: function (title, message, choices, options) {
        options = options || {};
        var buttons = '';

        for (var i in choices) {
            buttons += '<input type="button" class="button ' + (i == 'Cancel' ? 'gray' : 'black') + ' large" value="' + i + '" />';
        }

        Popup.show($.extend({
            html: '<div class="confirm">' +
                      '<h2>'+title+'</h2>' + (options.p !== false ? '<p>' : '')+message+(options.p !== false ? '</p>' : '') +
                      '<div class="confirm-buttons">' + buttons + '</div>' +
                  '</div>',
            width: 300
        }, options));

        $('#popup div.confirm-buttons input').each(function () {
            $(this).click(choices[this.value]);
        });

        return false;
    },

    positionPopup: function () {
        Popup.window().css({
            top: $(window).scrollTop() + (Popup.options.top ? Popup.options.top : 150),
            left: $(window).width() / 2 - Popup.window().width() / 2
        });
    },

    updateLayout: function () {
        if (Popup.overlay().css('display') != 'block') return;
        Popup.positionPopup();
        Popup.resizeOverlay();
    },

    resizeOverlay: function () {
        if (Popup.overlay().css('display') != 'block') return;
        Popup.overlay().hide().css({
            height: Math.max($(document).height(), $(window).height()),
            width: Math.max($(document).width(), $(window).width())
        }).show();
    },

    close: function () {
        $('select:not(#popup select)').css('visibility', 'visible');

        Popup.window().hide();
        Popup.overlay().hide();
        $(document).unbind('keydown', Popup.keydown);

        document.body.style.overflow = 'auto';
        Popup.options = null;

        return false;
    }
};

$(function () {
    $(window).resize(Popup.updateLayout);
});
