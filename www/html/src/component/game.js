class Game extends HTMLElement {
    constructor() {
        super();
        const shadow = this.attachShadow({ mode: "open" });
        shadow.appendChild(this.build());
        shadow.appendChild(this.styles());
    }

    build() {
        // component root
        const componentRoot = document.createElement("div");
        componentRoot.setAttribute("class", "container");

        // const templateStatic = this.templateTest();
        // componentRoot.innerHTML = templateStatic;
        
        this.httpRequest().then(res => {
            res.map(game => {
                let template = `
                <div class="card">
        
                    <div class="card__top">
                        <div class="card__top__left">
                            <img src="${game.backgroundImg}" alt="${game.slug}">
                        </div>
                        
                        <div class="card__top__right">
                            <h1>${game.title}</h1>
                            <span>Lançamento: ${this.convertDate(game.release)}</span>
                            <p>${game.descriptionRaw.substring(0, 700)}...</p>
                        </div>
                    </div>
            
                    <div class="card__bottom">
                        <div class="card__bottom__left">
                            <div class="card__bottom__left__rating">
                                <p>Rating Top: <span>${game.ratingTop}</span></p>
                                <p>Rating: <span>${game.rating}</span></p>
                            </div>
    
                            <div class="card__bottom__left__site">
                                <p>Website: <a href="${game.website}">${game.website}</a></p>
                            </div>
    
                            <div class="card__bottom__left__categories">
                                <p>Gênero: ${game.genres.map(g => ` <span>${g.name}</span>`)}</p>
                                <p>Tags: ${game.tags.map(t => ` <span>${t.name}</span>`)}</p>
                            </div>
    
                            <div class="card__bottom__left__platforms">
                                <p>Plataformas: ${game.platforms.map(p => ` <span>${p.name}</span>`)}</p>
                            </div>
                        </div>
                        <div class="card__bottom__right">
                            <card-topic user="amassouman" title="MEU DEUS EU VI O DAVY JONES">
                            </card-topic>
                            <card-topic user="davyjones" title="Fala galera aqui quem fala é o Davy Jones">
                            </card-topic>
                            <card-topic user="gameseduuu" title="Danonão grosso">
                            </card-topic>
                        </div>
                    </div>
                </div>
                `
                componentRoot.innerHTML += template;
            })
        })
        return componentRoot;
    }

    styles() {
        const styles = document.createElement("style");
        styles.textContent = `
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .card {
            display: grid;
            grid-template-columns: 1fr;
            row-gap: 1rem;
            width: 95%;
            padding: 10px;
            margin: 10px;
            border-radius: 20px;
            background: linear-gradient(125deg, #3D405B 0%, #4c67b8 50%, #3d9e71 100%);
        }

        .card__top {
            display: grid;
            grid-template-columns: 1fr 3fr;
            column-gap: 1rem;
        }
        
        .card__top__left,
        .card__top__right,
        .card__bottom__left {
            background-color:rgba(61, 64, 91, 0.2);
            border-radius: 10px;
            padding: 5px;
        }
        
        .card__top__left,
        .card__bottom__left {
            max-width: 26rem;
        }
        
        .card__top__left>img {
            display: block;
            max-width: inherit;
            margin-left: auto;
            margin-right: auto;
            border-radius: 10px;
        }

        .card__top__left>img:hover {
            cursor: pointer;
            filter: brightness(80%);
        }

        .card__top__right {
            display: flex;
            flex-direction: column;
        }

        .card__top__right>h1 {
            font-size: 2rem;
            line-height: 1rem;
        }

        .card__top__right>span {
            font-size: 16px;
            color: #bec1ec;
        }

        .card__bottom {
            display: grid;
            column-gap: 1rem;
            grid-template-columns: 1fr 2fr;
        }

        .card__bottom__left__rating p,
        .card__bottom__left__site p,
        .card__bottom__left__categories p,
        .card__bottom__left__platforms p {
            font-weight: 600;
        }
        
        .card__bottom__left__rating span:hover {
            cursor: pointer;
            color: #eeeb40;
        }

        .card__bottom__left__site>p>a {
            text-decoration: none;
            color: #e7876c;
            font-weight: 500;
        }

        .card__bottom__left__site>p>a:hover {
            cursor: pointer;
            text-decoration: underline;
        }

        .card__bottom__left__categories span,
        .card__bottom__left__platforms span {
            font-weight: 500;
            background-color: #4f537a;
        }

        .card__bottom__left__categories span:hover,
        .card__bottom__left__platforms span:hover {
            cursor: pointer;
            background-color: #2f3249;
        }

        .card__bottom__right {
            display: grid;
            grid-template-columns: 1fr 1fr;
            row-gap: 1rem;
            column-gap: 1rem;
        }
        `
        return styles;
    }

    convertDate(dateString) {
        var date = new Date(dateString);
        var dd = String(date.getDate()).padStart(2, '0');
        var mm = String(date.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = date.getFullYear();

        return dd + '/' + mm + '/' + yyyy;
    }

    httpRequest() {
        const url = "http://localhost:8080/api/rawg/game/entity";
        return fetch(url).then(res => res.json()).catch(err => {
            console.log("Erro ao acessar endpoint", err)
        });
    }

    templateTest() {
        let template = `
        <div class="card">
            <div class="card__top">
                <div class="card__top__left">
                    <img src="https://media.rawg.io/media/games/20a/20aa03a10cda45239fe22d035c0ebe64.jpg" alt="imagem">
                </div>
                <div class="card__top__right">
                    <h1>Titulo</h1>
                    <span>Lançamento: 27/02/2025</span>
                    <p>descrição...</p>
                </div>
            </div>
            <div class="card__bottom">
                <div class="card__bottom__left">
                    <div class="card__bottom__left__rating">
                        <p>Rating Top: <span>5</span></p>
                        <p>Rating: <span>5</span></p>
                    </div>
                    <div class="card__bottom__left__site">
                        <p>Website: <a href="">site</a></p>
                    </div>
                    <div class="card__bottom__left__categories">
                        <p>Gênero: <span>genero</span></p>
                        <p>Tags: <span>tag</span></p>
                    </div>
                    <div class="card__bottom__left__platforms">
                        <p>Plataformas: <span>PC, XBOX</span></p>
                    </div>
                </div>
                <div class="card__bottom__right">
                    <card-topic user="amasouman" title="MEU DEUS EU VI O DAVY JONES">
                    </card-topic>
                    <card-topic user="davyjones" title="Fala galera aqui quem fala é o Davy Jones">
                    </card-topic>
                    <card-topic user="gameseduuu" title="Danonão grosso">
                    </card-topic>
                </div>
            </div>
        </div>
        `
        return template;
    }
}

customElements.define("card-game", Game);