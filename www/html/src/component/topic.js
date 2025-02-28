class Topic extends HTMLElement {
    constructor() {
        super();
        const shadow = this.attachShadow({ mode: "open" })
        shadow.appendChild(this.build());
        shadow.appendChild(this.styles());

    }

    build() {
        const componentRoot = document.createElement("div");
        componentRoot.setAttribute("class", "card_topic");

        const user = document.createElement("span");
        const titleTopic = document.createElement("h2");
        const contentTopic = document.createElement("p");

        user.textContent = "@" + this.getAttribute("user");
        componentRoot.appendChild(user);

        titleTopic.textContent = this.getAttribute("title");
        componentRoot.appendChild(titleTopic);

        contentTopic.textContent = "Lorem ipsum, dolor sit amet consectetur adipisicing elit. Ex nihil, id doloribus consequatur vel dolores provident neque amet iure adipisci magni praesentium laudantium, reiciendis, dolorum ipsa quidem nam obcaecati voluptatum."
        componentRoot.appendChild(contentTopic);
        return componentRoot;
    }

    styles() {
        const style = document.createElement("style");
        style.textContent = `
        .card_topic {
            display: block;
            height: auto;
            padding: 5px;
            background-color:rgba(61, 64, 91, 0.2);
            border-radius: 10px;
        }
        
        span {
            color: #b8c3f7;
            font-size: .9rem;
        }

        span:hover {
            cursor: pointer;
            text-decoration: underline;
            color: #6ac9c9;
        }

        h2 {
            line-height: 0;
            font-size: 1rem;
        }

        h2:hover {
            filter: brightness(80%);
            cursor: pointer;
        }

        p {
            font-size: .95rem;
            line-height: 1.4rem;
        }
        
        `
        return style;
    }
}
customElements.define("card-topic", Topic);