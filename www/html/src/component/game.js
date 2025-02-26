class Game extends HTMLElement {
    constructor() {
        super();
        const shadow = this.attachShadow({ mode: "open" });
        shadow.appendChild(this.build());
        shadow.appendChild(this.style());
    }

    build() {
        // component root
        const componentRoot = document.createElement("div");
        componentRoot.setAttribute("class", "container");

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
                            <p>${game.descriptionRaw.substring(0, 500)}...</p>
                        </div>
                    </div>
            
                    <div class="card__bottom">
                        <div class="card__bottom__rating">
                            <p>Rating Top: <span>${game.ratingTop}</span></p>
                            <p>Rating: <span>${game.rating}</span></p>
                        </div>

                        <div class="card__bottom__site">
                            <p>Website: <a href="${game.website}">${game.website}</a></p>
                        </div>

                        <div class="card__bottom__categories">
                            <p>Gênero: ${game.genres.map(g => ` <span>${g.name}</span>`)}</p>
                            <p>Tags: ${game.tags.map(t => ` <span>${t.name}</span>`)}</p>
                        </div>

                        <div class="card__bottom__platforms">
                            <p>Plataformas: ${game.platforms.map(p => ` <span>${p.name}</span>`)}</p>
                        </div>
                    </div>
                </div>
                `
                componentRoot.innerHTML += template;
            })
        })
        return componentRoot;
    }

    style() {
        const styles = document.createElement("style");
        styles.textContent = `
        .container {
            display: flex;
            flex-direction: column;
        }

        .card {
            display: flex;
            flex-direction: column;
            max-width: 90%;
            padding: 5px;
            margin: 5px;
            border-radius: 20px;
            background-color: #464863;
        }

        .card__top {
            margin: 5px;
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            background-color: inherit;
        }

        .card__top__left,
        .card__top__right {
            width: 50%;
        }

        .card__top__left>img {
            width: 80%;
            height: auto;
            border-radius: 10px;
        }

        .card__top__left>img:hover {
            cursor: pointer;
            filter: brightness(90%);
        }

        .card__top__right {
            display: flex;
            flex-direction: column;
            gap: 5px;
            background-color: inherit;
        }

        .card__top__right>h1 {
            font-size: 2rem;
        }

        .card__top__right>span {
            font-size: 16px;
            color: #bec1ec;
        }

        .card__bottom__rating,
        .card__bottom__categories {
            display: flex;
            flex-direction: column;
            gap: 2px;
        }

        .card__bottom__rating p,
        .card__bottom__site p,
        .card__bottom__categories p,
        .card__bottom__platforms p {
            font-weight: 600;
        }

        .card__bottom__rating span:hover {
            cursor: pointer;
            color: #eeeb40;
        }

        .card__bottom__site>p>a {
            text-decoration: none;
            color: #e7876c;
            font-weight: 500;
        }

        .card__bottom__site>p>a:hover {
            cursor: pointer;
            text-decoration: underline;
        }

        .card__bottom__categories span,
        .card__bottom__platforms span {
            font-weight: 500;
            background-color: #4f537a;
        }

        .card__bottom__categories span:hover,
        .card__bottom__platforms span:hover {
            cursor: pointer;
            background-color: #2f3249;
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
}

customElements.define("card-game", Game);