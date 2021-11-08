const PROXY_CONFIG = [
  {
      context: [
          "/categories",
          "/etablissements",

      ],
      target: "https://api.position.cm",
      secure: false
  },
  {
    context: [
        "/auth",


    ],
    target: "https://services.position.cm",
    secure: false
}
]

module.exports = PROXY_CONFIG;
