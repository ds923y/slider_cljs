var countLayout = 'horizontal',
    alwaysShowCount = false;

function pinIt (pinItButton, pinUrl) {
    return function (e) {
        var t = e.srcElement;

        if (e.target) {
            t = e.target;
        }

        if (t.id != pinItButton.id) {
            return false;
        }

        var modal = window.open(pinUrl, 'signin', 'width=665,height=300'),
            wait = function () {
                setTimeout(function () {
                    if (modal == null) {
                        failure();
                        return;
                    }
                    if (modal.closed) {
                        pinItButton.setAttribute('class', 'PinItButton pinned');
                    } else {
                        wait();
                    }
                }, 25);
            };

        wait();
        return false;
    };
}

function receiveCount (data) {
    if (data['error']) return;

    var targetUrl = data['url'],
        pinItCount = document.getElementById('PinItCount'),
        countBubble = document.getElementById('CountBubble');

    if (!alwaysShowCount && data['count'] < 1 && countLayout === 'horizontal') return;

    pinItCount.style.display = 'block';

    var count = data['count'];

    if (count > 999 && count <= 999999) {
        count = Math.floor(count / 1000) + 'K+';
    } else if (count > 999999 && count <= 999999999) {
        count = Math.floor(count / 1000000) + 'M+';
    } else if (count > 999999999) {
        count = '++';
    }

    countBubble.innerHTML = count;
}