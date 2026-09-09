package com.laioffer.staybooking.model;


public enum UserRole {
   ROLE_HOST,
   ROLE_GUEST,
}


package com.laioffer.staybooking.model;


import jakarta.persistence.*;


import java.util.Objects;


@Entity
@Table(name = "users")
public class UserEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String username;
   private String password;
   @Enumerated(EnumType.STRING)
   private UserRole role;


   public UserEntity() {
   }


   public UserEntity(Long id, String username, String password, UserRole role) {
       this.id = id;
       this.username = username;
       this.password = password;
       this.role = role;
   }


   public String getPassword() {
       return password;
   }


   public String getUsername() {
       return username;
   }


   public Long getId() {
       return id;
   }


   public UserRole getRole() {
       return role;
   }


   @Override
   public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       UserEntity that = (UserEntity) o;
       return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && role == that.role;
   }


   @Override
   public int hashCode() {
       return Objects.hash(id, username, password, role);
   }


   @Override
   public String toString() {
       return "UserEntity{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", role=" + role +
               '}';
   }
}


package com.laioffer.staybooking.model;


import org.locationtech.jts.geom.Point;


import java.util.Objects;


@Entity
@Table(name = "listings")
public class ListingEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "host_id")
   private Long hostId;
   private String name;
   private String address;
   private String description;
   private Integer guestNumber;
   private List<String> imageUrls;
   private Point location;


   @ManyToOne
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JoinColumn(name = "host_id", foreignKey = @ForeignKey(name = "fk_listing_host"), insertable = false, updatable = false)
   private UserEntity host;


   public ListingEntity() {
   }


   public ListingEntity(Long id, Long hostId, String name, String address, String description, Integer guestNumber, List<String> imageUrls, Point location
   ) {
       this.id = id;
       this.hostId = hostId;
       this.name = name;
       this.description = description;
       this.address = address;
       this.guestNumber = guestNumber;
       this.imageUrls = imageUrls;
       this.location = location;
   }


   public Long getId() {
       return id;
   }


   public Long getHostId() {
       return hostId;
   }


   public String getName() {
       return name;
   }


   public String getDescription() {
       return description;
   }


   public String getAddress() {
       return address;
   }


   public Integer getGuestNumber() {
       return guestNumber;
   }


   public List<String> getImageUrls() {
       return imageUrls;
   }


   public Point getLocation() {
       return location;
   }


   public UserEntity getHost() {
       return host;
   }


   @Override
   public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       ListingEntity that = (ListingEntity) o;
       return Objects.equals(id, that.id) && Objects.equals(hostId, that.hostId) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(description, that.description) && Objects.equals(guestNumber, that.guestNumber) && Objects.equals(imageUrls, that.imageUrls) && Objects.equals(location, that.location) && Objects.equals(host, that.host);
   }


   @Override
   public int hashCode() {
       return Objects.hash(id, hostId, name, address, description, guestNumber, imageUrls, location, host);
   }


   @Override
   public String toString() {
       return "ListingEntity{" +
               "id=" + id +
               ", hostId=" + hostId +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", description='" + description + '\'' +
               ", guestNumber=" + guestNumber +
               ", imageUrls=" + imageUrls +
               ", location=" + location +
               ", host=" + host +
               '}';
   }
}


package com.laioffer.staybooking.model;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "bookings")
public class BookingEntity {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "guest_id")
   private Long guestId;
   @Column(name = "listing_id")
   private Long listingId;
   private LocalDate checkInDate;
   private LocalDate checkOutDate;


   @ManyToOne
   @OnDelete(action= OnDeleteAction.CASCADE)
   @JoinColumn(name = "listing_id", foreignKey = @ForeignKey(name = "fk_booking_listing"), insertable = false, updatable = false)
   private ListingEntity listing;


   @ManyToOne
   @OnDelete(action=OnDeleteAction.CASCADE)
   @JoinColumn(name = "guest_id", foreignKey = @ForeignKey(name = "fk_booking_guest"), insertable = false, updatable = false)
   private UserEntity guest;


   public BookingEntity() {
   }


   public BookingEntity(Long id, Long guestId, Long listingId, LocalDate checkInDate, LocalDate checkOutDate) {
       this.id = id;
       this.guestId = guestId;
       this.listingId = listingId;
       this.checkInDate = checkInDate;
       this.checkOutDate = checkOutDate;
   }


   public Long getId() {
       return id;
   }


   public Long getGuestId() {
       return guestId;
   }


   public Long getListingId() {
       return listingId;
   }


   public LocalDate getCheckInDate() {
       return checkInDate;
   }


   public LocalDate getCheckOutDate() {
       return checkOutDate;
   }


   public ListingEntity getListing() {
       return listing;
   }


   public UserEntity getGuest() {
       return guest;
   }


   @Override
   public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       BookingEntity that = (BookingEntity) o;
       return Objects.equals(id, that.id) && Objects.equals(guestId, that.guestId) && Objects.equals(listingId, that.listingId) && Objects.equals(checkInDate, that.checkInDate) && Objects.equals(checkOutDate, that.checkOutDate) && Objects.equals(listing, that.listing) && Objects.equals(guest, that.guest);
   }


   @Override
   public int hashCode() {
       return Objects.hash(id, guestId, listingId, checkInDate, checkOutDate, listing, guest);
   }


   @Override
   public String toString() {
       return "BookingEntity{" +
               "id=" + id +
               ", guestId=" + guestId +
               ", listingId=" + listingId +
               ", checkInDate=" + checkInDate +
               ", checkOutDate=" + checkOutDate +
               ", listing=" + listing +
               ", guest=" + guest +
               '}';
   }
}



package com.laioffer.staybooking.repository;


import com.laioffer.staybooking.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {


   UserEntity findByUsername(String username);


   boolean existsByUsername(String username);
}


package com.laioffer.staybooking.repository;


import com.laioffer.staybooking.model.ListingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.util.List;


public interface ListingRepository extends JpaRepository<ListingEntity, Long> {


   List<ListingEntity> findAllByHostId(Long hostId);


   @Query(value = """
           SELECT l.*
           FROM listings l
           LEFT JOIN bookings b
               ON l.id = b.listing_id
               AND b.check_in_date < :checkOut
               AND b.check_out_date > :checkIn
           WHERE ST_DWithin(CAST(l.location AS geography), CAST(ST_MakePoint(:lon, :lat) AS geography), :distance)
               AND l.guest_number >= :guestNum
               AND b.listing_id IS NULL;
           """, nativeQuery = true)
   List<ListingEntity> searchListings(double lat, double lon, double distance, LocalDate checkIn, LocalDate checkOut, int guestNum);
}


package com.laioffer.staybooking.repository;


import com.laioffer.staybooking.model.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.util.List;


public interface BookingRepository extends JpaRepository<BookingEntity, Long> {


   List<BookingEntity> findAllByGuestId(long guestId);


   List<BookingEntity> findAllByListingId(long listingId);


   @Query(value = "SELECT * FROM bookings WHERE listing_id = :listingId AND check_in_date < :checkOut AND check_out_date > :checkIn", nativeQuery = true)
   List<BookingEntity> findOverlappedBookings(long listingId, LocalDate checkIn, LocalDate checkOut);


   boolean existsByListingIdAndCheckOutDateAfter(long listingId, LocalDate date);
}



package com.laioffer.staybooking;


import com.laioffer.staybooking.model.BookingEntity;
import com.laioffer.staybooking.model.ListingEntity;
import com.laioffer.staybooking.model.UserEntity;
import com.laioffer.staybooking.model.UserRole;
import com.laioffer.staybooking.repository.BookingRepository;
import com.laioffer.staybooking.repository.ListingRepository;
import com.laioffer.staybooking.repository.UserRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.List;


@Component
public class DevRunner implements ApplicationRunner {


   static private final Logger logger = LoggerFactory.getLogger(DevRunner.class);


   private final BookingRepository bookingRepository;
   private final ListingRepository listingRepository;
   private final UserRepository userRepository;


   public DevRunner(
           BookingRepository bookingRepository,
           ListingRepository listingRepository,
           UserRepository userRepository
   ) {
       this.bookingRepository = bookingRepository;
       this.listingRepository = listingRepository;
       this.userRepository = userRepository;
   }


   @Override
   public void run(ApplicationArguments args) {
       generateSampleData();


       UserEntity user = userRepository.findByUsername("rich_the_landlord");
       logger.info(user.toString());


       boolean exists = userRepository.existsByUsername("eddy");
       logger.info("User eddy exists? " + exists);


       List<ListingEntity> johnListings = listingRepository.findAllByHostId(1L);
       logger.info("Host rich_the_landlord has the following listings: " + johnListings);


       List<ListingEntity> availableListings = listingRepository.searchListings(
               37.55093541777099,
               -122.32841093538015,
               100000,
               LocalDate.now().plusDays(5),
               LocalDate.now().plusDays(10),
               2
       );
       logger.info("Available listings: " + availableListings.stream().map(
               entity -> entity.getId() + " " + entity.getName()
       ).toList());


       List<BookingEntity> guestBookings = bookingRepository.findAllByGuestId(4L);
       logger.info("Guest lively_wanderlust has the following bookings: " + guestBookings);


       List<BookingEntity> listingBookings = bookingRepository.findAllByListingId(1L);
       logger.info("Listing 1 has the following bookings: " + listingBookings);


       List<BookingEntity> overlappedBookings = bookingRepository.findOverlappedBookings(
               1L,
               LocalDate.now().plusDays(5),
               LocalDate.now().plusDays(10)
       );
       logger.info("Listing 1 overlapped bookings: " + overlappedBookings);


       boolean hasUpcomingBooking = bookingRepository.existsByListingIdAndCheckOutDateAfter(
               1L,
               LocalDate.now()
       );
       logger.info("Listing 1 has upcoming booking? " + hasUpcomingBooking);
   }


   private void generateSampleData() {
       userRepository.saveAll(List.of(
               new UserEntity(null, "rich_the_landlord", "YT61cW", UserRole.ROLE_HOST),
               new UserEntity(null, "starry_nights", "sa4NiK", UserRole.ROLE_HOST),
               new UserEntity(null, "eternal_optimist", "akKKN6", UserRole.ROLE_HOST),
               new UserEntity(null, "lively_wanderlust", "1z3dUW", UserRole.ROLE_GUEST),
               new UserEntity(null, "cool_coder_dude", "0k/N1i", UserRole.ROLE_GUEST),
               new UserEntity(null, "thunderstorm_rider", "C5AB0.", UserRole.ROLE_GUEST),
               new UserEntity(null, "victorious_soul", "UhjaYO", UserRole.ROLE_GUEST),
               new UserEntity(null, "music_maniac", "BrSZo.", UserRole.ROLE_GUEST),
               new UserEntity(null, "rainbow_skies", "P4fTkW", UserRole.ROLE_GUEST),
               new UserEntity(null, "moonlight_dancer", "64zzLG", UserRole.ROLE_GUEST),
               new UserEntity(null, "melody_walker", "K7P/Oe", UserRole.ROLE_GUEST),
               new UserEntity(null, "cosmic_traveler", "jFG/qm", UserRole.ROLE_GUEST),
               new UserEntity(null, "eternal_dreamer", "4itNFi", UserRole.ROLE_GUEST)
       ));


       GeometryFactory geometryFactory = new GeometryFactory();


       listingRepository.saveAll(List.of(
               new ListingEntity(
                       null,
                       1L,
                       "Tree House (Golden Gate Bridge)",
                       "123 Happy Tree St",
                       "Escape to a cozy tree house nestled in nature",
                       2,
                       List.of("https://example.com/treehouse1.jpg", "https://example.com/treehouse2.jpg"),
                       geometryFactory.createPoint(new Coordinate(-122.4787, 37.8199))
               ),
               new ListingEntity(
                       null,
                       1L,
                       "Luxurious Mansion (Sonoma Plaza)",
                       "567 Opulent Estate Ave",
                       "Indulge in the ultimate luxury in this magnificent mansion",
                       10,
                       List.of("https://example.com/mansion1.jpg", "https://example.com/mansion2.jpg", "https://example.com/mansion3.jpg"),
                       geometryFactory.createPoint(new Coordinate(-122.4584, 38.2921))
               ),
               new ListingEntity(
                       null,
                       2L,
                       "Charming Cottage (Union Square)",
                       "789 Serene Lane",
                       "Experience rustic charm in this delightful cottage",
                       4,
                       List.of("https://example.com/cottage1.jpg"),
                       geometryFactory.createPoint(new Coordinate(-122.4075, 37.7881))
               ),
               new ListingEntity(
                       null,
                       2L,
                       "Beachfront Villa (Santa Clara Convention Center)",
                       "456 Seaside Blvd",
                       "Enjoy breathtaking ocean views in this stunning beachfront villa",
                       6,
                       List.of("https://example.com/villa1.jpg", "https://example.com/villa2.jpg"),
                       geometryFactory.createPoint(new Coordinate(-121.9978, 37.3538))
               ),
               new ListingEntity(
                       null,
                       2L,
                       "Modern Apartment (Santana Row)",
                       "321 Urban Street",
                       "Stay in a sleek and stylish apartment in the heart of the city",
                       2,
                       List.of("https://example.com/apartment1.jpg", "https://example.com/apartment2.jpg", "https://example.com/apartment3.jpg"),
                       geometryFactory.createPoint(new Coordinate(-121.9481, 37.3205))
               ),
               new ListingEntity(
                       null,
                       3L,
                       "Mountain Retreat (Napa Valley Vineyards)",
                       "987 Tranquil Trail",
                       "Escape to a peaceful mountain retreat surrounded by nature",
                       8,
                       List.of("https://example.com/retreat1.jpg", "https://example.com/retreat2.jpg"),
                       geometryFactory.createPoint(new Coordinate(-122.2869, 38.2975))
               )
       ));


       LocalDate today = LocalDate.now();
       bookingRepository.saveAll(List.of(
               new BookingEntity(null, 4L, 1L, today.minusDays(20), today.minusDays(10)),
               new BookingEntity(null, 4L, 2L, today.plusDays(1), today.plusDays(5)),
               new BookingEntity(null, 5L, 1L, today.plusDays(5), today.plusDays(10)),
               new BookingEntity(null, 6L, 1L, today.plusDays(12), today.plusDays(16)),
               new BookingEntity(null, 7L, 1L, today.plusDays(16), today.plusDays(22)),
               new BookingEntity(null, 8L, 3L, today.plusDays(4), today.plusDays(7)),
               new BookingEntity(null, 9L, 4L, today.plusDays(2), today.plusDays(40))
       ));
   }
}
