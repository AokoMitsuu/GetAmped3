	� G   ��       �V{O�F�;�|�m�*��1�^e)W�J)� ����Y'��^k����|���7�𣼊�+?�=���_�Z�t����\�q'IF�Ӑ�L�[�[4�h��ԝc����1yAX�9Z���,V����(�S)p\�U*�T�˫�����|���0��������P}�q���.,���V'f�� ���� �W�;�wv��杳9�QT�)�FhA�)I	ǂX�����?��"�m#�h[R"�yn<-�1]�9�����o��:J�)�d˛q�s����Qr?��q�����E�����äl��!�:[�]e1�	�u�U$5A���M��P�p�G]M�m�"fܢ#/�G����Shw2�B�����֦�I͑r� Y��.�*���`ˉ(x�8��̅�&7���"�%E���~B�ڋ�O%����#���YR<j��Ã����-g+�Z�����*H�;::�k���#~+拄�b�8\^c}=SaU�J���s뉽�B�[������k5���o��7$�Sd�A��S��ŝ���T���R�*BU�q���JJC�b�!)�g�Ϡr���� H���b���	�X�m�}*դ�4��Q��hG��;Ϯ�����g�ʚfk�5�v(�vX�kA���Z_�5�	|��9�k������I���`&	^���J�c�.��j�!�*߄����J�f���N9��i��Et-]��]����Ƥ�޵�������Q$�0T�u���d�R6'\�,��l���\]\[��J]��1KM�7J2����ZV@Ӵܿ*=X����	p���b\N��7� ��s��S�gjf��+��	3W4�0�X�AfI�*��+k�ƀ��L����&{��Fr��;�����4��pS�詇2�WECy�cb\x���	lrI8P�K*�0��.<�j�݊I"�|���!����5�~��VĔeW��U�9(*h&��3�
�$\�y� M1���ފ�Qp=BQ��^���Չ���33@o���D���XynK2-��b������"f��`V�x��D�B���|)GwlZ��r��w��W�����d&~PW�E�+ʱ��G��rV�>������#�a��T|��}�h~����@?ߐ ���ܼ�?�0k�~h�^/Ν\��TO-.� �8�Z�   ~  Lga2/stage/AmpedScript; e PROCESS_MAX 
ATTACK_MAX start process approachtarget broken lockItemScript (ZZ)V
 f m n getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d p q Lkotori/gfx/KGLScriptModel; s kill
 t u 1
 H 0 3 setShadowState
 d x 3 tempInt (I)Ljava/lang/Integer;
 d z { generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d } ~D /accs/fiona_gun/funnel_l.scm�            ?�  ?�  ?�               funnel_F_x2 clearGenerate
 d � 1 setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � � temp ()Ljava/lang/Object;
 d � � Ljava/lang/Integer; � intValue
 � � 2��� setJudgmentApproachTarget (F)V
 f � �A    clearJudgmentKind
 f � 1 getArgv
 f � � getPosition ()Lkotori/geom/Position;
 f � �  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D (F)F
 � � � Ljava/lang/Math; � round (F)I
 � � � ()F
 f � �C4  C�  �4  	 � R  length
 � � � (FF)F
 � � � min
 � � �B4  �� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone
 � � � getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � rotate (Lkotori/geom/Quaternion;I)V
 t � � 	playSound (IIFF)V
 d � � (II)V
 d � � startScript (Ljava/lang/String;)Z
 t � � funnel_shot2 	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            �x� �D $/accs/fiona_gun/funnel_F_x2_shot.scm�            ?�  ?�  ?�              � hit_fiona_gun  d� ,    FP�       ,�>�         (Z)V
 f m � /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d } �D /efx/flash/glow01_yel3.ptc�            ?�  ?�  ?�               funnel_F_x2_endD /efx/flash/glow01_yel3.ptc�            ?   @   ?               D  /efx/kirakira/fairy_kirakira.scm�            @   @   @                kirakira2_yellowD /efx/flash/glow01_yel3.ptc�            >L��?�  >L��            D /efx/ripple/ripple05_yel3.ptc�            ?�  ?�  ?�              
 f u 1 
stopScript
 f � 1 f g  h  - 1  � ��  i 1  c*� oW*� rL+� +� vW*d� wW*� yW*� |W*��� L*� �W*+� �W*�� wW*� oW=� *� W�W� ���  j 1  o*� �L+� �+� �=|�~>~6� � 
�� *� W�W*x�� |W* ȶ wW*�� �W*X� wW*� �W* ȶ wW� � *� W�  k 1 *� �L+� �=|�~>~6�W*x�� |W*� �:*� �:� �W� �� �� ��8*� �� �� ��8��� 
�f8��� 
�f8f8		��� 	�f8	� 	��� 
	�b8	� �8
� �� �8
� �� �� �Zd�8�� #bj8bj�� ��nj8b8�:� �:	� �W*� r:� � �W* ȶ wW* �b� �W* �� �W� ˶ �W*� �W*�Ӷ �W*� �W*�� wW6� *� W�W� `d����  l 1  c*� �W*� �W*ض �W*2� wW*� rL+� 
+ٶ �W*ڶ �W*�ܶ W*d� wW*ݶ �W*޶ �W*�� wW*� �W*� �W� m��                        �  