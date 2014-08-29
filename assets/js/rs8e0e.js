var RS = {
    rstyleMe: 'http://rstyle.me/',
    rstyleMePin: function () {
        if (window.location.href.indexOf('dev')) {
            return 'http://rstyle.me/n/';
        } else {
            return 'http://dev.rstyle.me/n/';
        }
    },
    userId: 0,
    encode: function (product, user) {
        if (product < 0 || user < 0) return;

        var ch = 'abcdefghijkmnpqrstuvwxyz23456789'.split(''),
            lower4 = user << 3,
            upper4 = product;
            log = Math.log(lower4) / Math.log(2),
            lower4Bits = Math.floor(log) + 1,
            str = '';

        lower4 |= Math.ceil(lower4Bits / 5);

        do {
            str = ch[lower4 & 31] + str;
        } while (lower4 >>= 5);

        do {
            str = ch[upper4 & 31] + str;
        } while (upper4 >>= 5);

        return str;
    },
    referralInvite: function () {

        Popup.show({
            url: '/ads/invite',
            width: 550
        });
        return false;
    }
};

$(function () {
    $('input[title], textarea[title]').bind('focus', function () {
        var $this = $(this);
        if ($this.val() == $this.attr('title')) $this.val('').removeClass('temp');
    }).bind('blur', function () {
        var $this = $(this);
        if ($this.val() == '') $this.val($this.attr('title'));
        if ($this.val() == $this.attr('title')) $this.addClass('temp');
    }).each(function () {
        var $this = $(this);
        if ($this.attr('title')) $this.val($this.attr('title')).addClass('temp');
    }).bind('change', function () {
        var $this = $(this);
        if ($this.attr('title')) $this.removeClass('temp');
    });
    $.ajaxSetup({
        error: function (xhr, more, m) {
            if (xhr.status == 403) window.location.href = window.location.href + (window.location.href.indexOf('?') >= 0 ? '&' : '?') + 'timeout';
        }
    });
});

$.fn.noop = function () {
    return this;
};

Function.prototype.bind = function (obj) {
    var _this = this;
    return function () {
        _this.apply(obj, arguments);
    };
};

jQuery.expr[':'].icontains = function (a, i, m) {
    return (a.innerText || a.textContent || '').toUpperCase().indexOf(m[3].toUpperCase()) >= 0;
};

jQuery.fn.sortElements = (function () {
    var sort = [].sort;
    return function (comparator, getSortable) {
        getSortable = getSortable || function () {
            return this;
        };
        var placements = this.map(function () {
            var sortElement = getSortable.call(this),
                parentNode = sortElement.parentNode,
                // Since the element itself will change position, we have
                // to have some way of storing its original position in
                // the DOM. The easiest way is to have a 'flag' node:
                nextSibling = parentNode.insertBefore(
                document.createTextNode(''),
                sortElement.nextSibling);
            return function () {
                if (parentNode === this) {
                    throw new Error("You can't sort elements if any one is a descendant of another.");
                }
                // Insert before flag:
                parentNode.insertBefore(this, nextSibling);
                // Remove flag:
                parentNode.removeChild(nextSibling);
            };
        });
        return sort.call(this, comparator).each(function (i) {
            placements[i].call(getSortable.call(this));
        });
    };
})();

if (!Array.prototype.indexOf) Array.prototype.indexOf = function (obj, start) {
    for (var i = (start || 0), j = this.length; i < j; i++) {
        if (this[i] === obj) {
            return i;
        }
    }
    return -1;
}

function htmlEncode(value) {
    return value.toString().replace(/</g, '&gt;').replace(/>/g, '&lt;').replace(/&/g, '&amp;').replace(/"/g, '&quot;').replace(/'/g, '&apos;');
}

function htmlDecode(value) {
    return value.toString().replace(/&gt;/g, '>').replace(/&lt;/g, '<').replace(/&amp;/g, '&').replace(/&quot;/g, '"').replace(/&apos;/g, "'");
}
