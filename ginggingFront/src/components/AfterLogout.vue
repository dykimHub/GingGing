<template>
    <div class="wrapper">
        <div class="image-container">
            <img
                src="@/assets/LogowithTitle.png"
                class="background-image"
                style="width: 100%; height: 100%"/>
        </div>
        <div class="typing-demo">
            Wish you had a healthy<br/>
            time with GINGGING!
        </div>
    </div>
</template>

<script setup></script>

<style scoped>
.wrapper {
    height: 60vh;
    display: grid;
    place-items: center;
    position: relative;
}
.image-container {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 500px;
    height: 500px;
    z-index: -1;
}
.background-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
    opacity: 0.3;
}
.typing-demo {
    width: 22ch;
    animation: typing 2s steps(22), blink 0.5s step-end infinite alternate;
    white-space: nowrap;
    overflow: hidden;
    border-right: 3px solid;
    font-family: monospace;
    font-size: 2.3em;
    font-family: "GmarketSansMedium";
    font-weight: 1000;
    z-index: 1;
}

@keyframes typing {
    from {
        width: 0;
    }
}

@keyframes blink {
    50% {
        border-color: transparent;
    }
}
</style>